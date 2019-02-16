package com.rocky.latte.core.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rocky.latte.core.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/15
 */

public class MultipleRecyclerviewAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup {

    private final boolean isInitBanner = false;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclerviewAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    public MultipleRecyclerviewAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerviewAdapter(data);
    }

    public MultipleRecyclerviewAdapter create(DataConverter converter) {
        return new MultipleRecyclerviewAdapter(converter.convert());
    }

    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (helper.getItemViewType()) {
            case ItemType.TEXT:
                text = item.getField(MultipleFields.TEXT);
                helper.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .into((ImageView) helper.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getField(MultipleFields.TEXT);
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .into((ImageView) helper.getView(R.id.img_multiple));
                helper.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if (!isInitBanner){
                    bannerImages = item.getField(MultipleFields.BANNERS);
                    final  ConvenientBanner<String> banner = helper.getView(R.id.banner_recycler_item);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
