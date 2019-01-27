package com.rocky.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)//在源码阶段处理
public @interface AppRegisterGenerator {

    String packageName();

    Class<?> registerTemplete();
}
