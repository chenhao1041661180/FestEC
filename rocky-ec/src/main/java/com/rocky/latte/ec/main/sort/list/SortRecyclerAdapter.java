package com.rocky.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.main.sort.SortDelegate;
import com.rocky.latte.ec.main.sort.content.SortContentDelegate;
import com.rocky.latte.ui.recycler.ItemType;
import com.rocky.latte.ui.recycler.MultipleFields;
import com.rocky.latte.ui.recycler.MultipleItemEntity;
import com.rocky.latte.ui.recycler.MultipleRecyclerviewAdapter;
import com.rocky.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/20
 */

public class SortRecyclerAdapter extends MultipleRecyclerviewAdapter {
    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity item) {
        super.convert(holder, item);
        final String text = item.getField(MultipleFields.TEXT);
        final boolean isClicked = item.getField(MultipleFields.TAG);
        holder.setText(R.id.tv_vertical_item_name, text);
        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (mPrePosition != adapterPosition) {
                    getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                    notifyItemChanged(mPrePosition);
                    //更新选中的item
                    item.setField(MultipleFields.TAG, true);
                    notifyItemChanged(adapterPosition);
                    mPrePosition = adapterPosition;

//                    final int id = item.getField(MultipleFields.ID);
                    final int contentId = getData().get(adapterPosition).getField(MultipleFields.ID);
                    showContent(contentId);
                }
            }
        });

        if (!isClicked) {
            holder.setVisible(R.id.view_line, false);
            holder.setTextColor(R.id.tv_vertical_item_name, ContextCompat.getColor(mContext, R.color.we_chat_black));
            itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
        } else {
            holder.setVisible(R.id.view_line, true);
            holder.setTextColor(R.id.tv_vertical_item_name,
                    ContextCompat.getColor(mContext, R.color.app_main));
            holder.setBackgroundColor(R.id.view_line, ContextCompat.getColor(mContext, R.color.app_main));
            itemView.setBackgroundColor(Color.WHITE);
        }

    }

    private void showContent(int contentId) {
        SortContentDelegate delegate = SortContentDelegate.newInstance(contentId);
        switchDelegate(delegate);

    }

    /**
     * 切换内容的fragment
     *
     * @param delegate
     */
    private void switchDelegate(SortContentDelegate delegate) {

        final LatteDelegate contentDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), SortContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);

        }
    }
}
