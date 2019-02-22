package com.rocky.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/21
 */

public class SectionDataConverter {
    public static List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray array = JSON.parseObject(json).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i <size; i++) {
            JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");
            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);

            JSONArray goodsArray = data.getJSONArray("goods");
            final int goodsSize = goodsArray.size();
            for (int j = 0; j <goodsSize ; j++) {
                JSONObject goods = goodsArray.getJSONObject(j);

                final int goodsId = goods.getInteger("goods_id");
                final String goodsName = goods.getString("goods_name");
                final String goodsThumb = goods.getString("goods_thumb");
                //获取内容
                SectionContentItemEntity entity = new SectionContentItemEntity();
                entity.setGoodsId(goodsId);
                entity.setGoodsName(goodsName);
                entity.setGoodsThumb(goodsThumb);
                dataList.add(new SectionBean(entity));
            }
        }
        return dataList;
    }
}
