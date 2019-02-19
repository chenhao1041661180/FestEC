package com.rocky.latte.ec.main.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.ec.R;
import com.rocky.latte.ui.recycler.RgbValue;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/18
 */
@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;
    private final RgbValue RGB_VALUE = RgbValue.create(255,124,2);
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar toolbar, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, toolbar, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        final int startOffset = 0;
        final Context context = Latte.getApplicationContext();
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
        mOffset += dyConsumed;
        int tartgetHeight =toolbar.getBottom();
        if (mOffset <= tartgetHeight) {
            final float percent = (float) mOffset / tartgetHeight;
            final int alpha = Math.round(percent * 255);
            toolbar.setBackgroundColor(Color.argb(alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        } else {
            toolbar.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
//            toolbar.getBackground().setAlpha(255);
        }
//        if (mOffset <= startOffset) {
//            toolbar.getBackground().setAlpha(0);
//        } else if (mOffset > startOffset && mOffset < endOffset) {
//            final float percent = (float) (mOffset - startOffset) / endOffset;
//            final int alpha = Math.round(percent * 255);
//            toolbar.getBackground().setAlpha(alpha);
//        } else if (mOffset >= endOffset) {
//            toolbar.getBackground().setAlpha(255);
//        }
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }
}
