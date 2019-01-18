package com.rocky.latte.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.rocky.latte.core.R;
import com.rocky.latte.core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.BallClipRotateMultipleIndicator;

import java.util.ArrayList;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/17
 */

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallGridPulseIndicator.name();

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    private static AppCompatDialog showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, type);
        final Window dialogWindow = dialog.getWindow();
        int screenWidth = DimenUtil.getScreenWidth();
        int screenHeight = DimenUtil.getScreenHeight();
        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = screenWidth/LOADER_SIZE_SCALE;
            layoutParams.height = screenHeight/LOADER_SIZE_SCALE;
            layoutParams.height = layoutParams.height + screenHeight / LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;

        }
        dialog.setContentView(avLoadingIndicatorView);
        LOADERS.add(dialog);
        dialog.show();
        return dialog;
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        }
        LOADERS.clear();
    }
}
