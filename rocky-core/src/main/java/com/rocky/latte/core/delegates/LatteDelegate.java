package com.rocky.latte.core.delegates;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }
}
