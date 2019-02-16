package com.rocky.latte.core.ui.recycler;

import java.util.ArrayList;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("data is null!");
        }
        return mJsonData;
    }
}
