package com.jaiz.study.beans;

import java.util.Objects;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Example {

    /**
     * 类型
     */
    private CategoryType type;
    /**
     * 标题，例如 lesson001
     */
    private String title;
    /**
     * 副标题 hello, javafx!
     */
    private String subtitle;
    /**
     * Startable实现
     */
    private Class<? extends Startable> appClazz;

    /**
     * 摘要
     */
    private String[] digest;

    public static Example fromClass(Class<? extends Startable> appClass) {
        Example e = new Example();
        e.setAppClazz(appClass);
        StartableMeta meta = appClass.getAnnotation(StartableMeta.class);
        if (Objects.nonNull(meta)) {
            e.setTitle(meta.title());
            e.setSubtitle(meta.subtitle());
            e.setDigest(meta.digest());
            e.setType(meta.category());
        }
        return e;
    }

}