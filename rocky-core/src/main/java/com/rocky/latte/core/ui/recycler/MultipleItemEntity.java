package com.rocky.latte.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import retrofit2.http.PUT;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder() {
        return  new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    public static class MultipleEntityBuilder {
        private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

        public MultipleEntityBuilder() {
            FIELDS.clear();
        }

        public MultipleEntityBuilder setItemType(int itemType) {
            FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
            return this;
        }

        public MultipleEntityBuilder setField(Object key, Object value) {
            FIELDS.put(key, value);
            return this;
        }

        public MultipleEntityBuilder setFields(LinkedHashMap<?, ?> fields) {
            FIELDS.putAll(fields);
            return this;
        }

        public MultipleItemEntity build() {
            return new MultipleItemEntity(FIELDS);
        }
    }
}
