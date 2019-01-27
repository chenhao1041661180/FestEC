package com.rocky.festec.latte.generators;

import com.rocky.annotations.PayEntryGenerator;
import com.rocky.latte.ec.wechat.templates.WXPayEntryTemplate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.rocky.festec.latte",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WXPayEntry {
}
