package com.rocky.latte.ec.ui.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.util.LatteLogger;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;
import com.rocky.latte.ec.db.DaoManager;
import com.rocky.latte.ec.entity.User;
import com.rocky.latte.ec.wechat.LatteWeChat;
import com.rocky.latte.ec.wechat.callback.IWeChatSignCallback;

import java.util.List;

import butterknife.BindView;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {
    @BindView(R2.id.edit_sign_in_email)
    public TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_in_password)
    public TextInputEditText mPassword;
    private ISignListener iSignListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener)
            iSignListener = (ISignListener) context;

    }

    //    private ISignListener mISignListener = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    private void onClickLink() {
        startWithPop(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        $(R.id.btn_sign_in).setOnClickListener(this);
        $(R.id.tv_link_sign_up).setOnClickListener(this);
        $(R.id.icon_sign_in_wechat).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) {
            onClickWeChat();
        }
    }

    private void onClickWeChat() {
        LatteWeChat.getInstance().onSignSuccess(new IWeChatSignCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    private void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("mock/data/user_profile.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .callback(new ResponseCallback() {
                        @Override
                        public void onSuccess(String message) {
                            LatteLogger.json("USER_PROFILE", message);
                            LatteLogger.i("USER_PROFILE", "onSuccess:" + message);
                            SignHandler.onSignIn(message, iSignListener);
                        }

                        @Override
                        public void onFailed() {
                            Toast.makeText(Latte.getApplicationContext(), "onFailed", Toast.LENGTH_SHORT).show();
                            LatteLogger.i("USER_PROFILE", "onFailed");
                        }

                        @Override
                        public void onError(int code, String msg) {
                            LatteLogger.i("USER_PROFILE", "onError" + msg);
                        }
                    })
                    .build()
                    .get();
        }
    }
}
