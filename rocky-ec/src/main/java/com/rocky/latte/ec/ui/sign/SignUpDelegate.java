package com.rocky.latte.ec.ui.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.util.LatteLogger;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    public TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    public TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    public TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    public TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    public TextInputEditText mRePassword;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        $(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });

        $(R.id.tv_link_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLink();
            }
        });

    }
    private void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("/mock/data/user_profile.json")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .callback(new ResponseCallback() {
                        @Override
                        public void onSuccess(String message) {
                            LatteLogger.json("USER_PROFILE", message);
//                            SignHandler.onSignUp(response, mISignListener);
                        }

                        @Override
                        public void onFailed() {
                            LatteLogger.i("USER_PROFILE","onFailed");
                        }

                        @Override
                        public void onError(int code, String msg) {
                            LatteLogger.i("onError", msg);
                        }
                    })
                    .build()
                    .post();
        }
    }
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    private void onClickLink() {
     startWithPop(new SignInDelegate());
    }
}
