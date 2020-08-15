package com.jaiz.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 示例窗口元信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartableMeta {

    /**
     * 范畴，例如
     * lesson、example
     */
    CategoryType category();

    /**
     * 标题，例如
     * lesson001
     */
    String title();

    /**
     * 副标题，例如
     * javafx的生命周期
     * 可以不填
     */
    String subtitle() default "";

    /**
     * 简单内容提要
     */
    String[] digest() default {};

}