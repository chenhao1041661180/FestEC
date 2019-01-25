package com.rocky.latte.ec.ui.sign;

import com.alibaba.fastjson.JSON;
import com.rocky.latte.core.util.LatteLogger;
import com.rocky.latte.ec.db.DaoManager;
import com.rocky.latte.ec.entity.User;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */

public class SignHandler {

    public static void onSignUp(String json) {
        User user = JSON.parseObject(json, User.class);
        DaoManager.getInstance().getUserDao().insert(user);
    }
    public static void onSignIn(String json) {
        User user = JSON.parseObject(JSON.parseObject(json).getString("data"),User.class);
        LatteLogger.i(user.getName());
        DaoManager.getInstance().getUserDao().insertOrReplace(user);
    }
}
