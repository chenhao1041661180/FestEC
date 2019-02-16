package com.rocky.latte.ec.main.home;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rocky.latte.ui.recycler.DataConverter;
import com.rocky.latte.ui.recycler.ItemType;
import com.rocky.latte.ui.recycler.MultipleFields;
import com.rocky.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/15
 */

public class HomeDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        JSONArray jsonArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spansize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray bannerArray = data.getJSONArray("banners");
            int type = 0;
            final ArrayList<String> bannerImages = new ArrayList<>();
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text==null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (bannerArray != null) {
                type = ItemType.BANNER;
                int bannerSize = bannerArray.size();

                for (int j = 0; j < bannerSize; j++) {
                    final  String bannerImage = bannerArray.getString(j);
                    bannerImages.add(bannerImage);
                }
            }
            final MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.SPAN_SIZE,spansize)
                    .build();
            ENTITIES.add(itemEntity);

        }
        return ENTITIES;
    }
}
