package com.rocky.latte.ec.main.sort.list;

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
 * @date 2019/2/20
 */

public class SortListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i <size ; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG, true);
        }
        return dataList;
    }
}
