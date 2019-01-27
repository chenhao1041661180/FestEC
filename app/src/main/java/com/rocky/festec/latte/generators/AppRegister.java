package com.rocky.festec.latte.generators;

import com.rocky.annotations.AppRegisterGenerator;
import com.rocky.latte.ec.wechat.templates.AppRegisterTemplate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.rocky.festec.latte",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
