package com.rocky.latte.core.sign;

import com.rocky.latte.core.util.storage.LattePreference;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    public static final void setSignState(boolean isSign){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),isSign);
    }

    public static final boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker iUserChecker){
        if (isSignIn())
            iUserChecker.onSignIn();
        else
            iUserChecker.onNotSignIn();

    }
}
