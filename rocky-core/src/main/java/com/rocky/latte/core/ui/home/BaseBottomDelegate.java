package com.rocky.latte.core.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.rocky.latte.core.R;
import com.rocky.latte.core.R2;
import com.rocky.latte.core.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener{

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BaseHomeDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BaseHomeDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;
    private int mNormalColor = Color.GRAY;
    @BindView(R2.id.bottom_bar)
    public LinearLayoutCompat mBottomBar;

    @Override
    public Object setLayout() {
        return R.layout.bottom_delegate;
    }

    public abstract LinkedHashMap<BottomTabBean, BaseHomeDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        mCurrentDelegate = mIndexDelegate;
        if (setClickedColor() != 0)
            mClickedColor = setClickedColor();

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BaseHomeDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BaseHomeDelegate> item : ITEMS.entrySet()) {
            TAB_BEANS.add(item.getKey());
            ITEM_DELEGATES.add(item.getValue());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView icon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView title = (AppCompatTextView) item.getChildAt(1);
            final  BottomTabBean bean = TAB_BEANS.get(i);
            icon.setText(bean.getIcon());
            title.setText(bean.getTitle());
            icon.setTextColor(i == mIndexDelegate ? mClickedColor : mNormalColor);
            title.setTextColor(i == mIndexDelegate ? mClickedColor : mNormalColor);
        }
        final SupportFragment [] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);

    }
    private void resetColor(){
        int size = mBottomBar.getChildCount();
        for (int i = 0; i < size; i++) {
            RelativeLayout relativeLayout = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView icon = (IconTextView) relativeLayout.getChildAt(0);
            AppCompatTextView title = (AppCompatTextView) relativeLayout.getChildAt(1);
            icon.setTextColor(i == mCurrentDelegate ? mClickedColor : mNormalColor);
            title.setTextColor(i == mCurrentDelegate ? mClickedColor : mNormalColor);

        }
    }

    /**
     * 处理选中的tab颜色
     * @param tabIndex
     */
    public void changeColor(int tabIndex) {
        resetColor();
        final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(tabIndex);
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
    }
    @Override
    public void onClick(View v) {
        final int tabIndex = (int) v.getTag();

        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tabIndex), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tabIndex;
//        changeColor(tabIndex);
        resetColor();
    }
}
