package com.rocky.festec.latte.generators;

import com.rocky.annotations.EntryGenerator;
import com.rocky.latte.ec.wechat.templates.WXEntryTemplate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.rocky.festec.latte",
        entryTemplete = WXEntryTemplate.class
)
public interface WXEntry {
}
