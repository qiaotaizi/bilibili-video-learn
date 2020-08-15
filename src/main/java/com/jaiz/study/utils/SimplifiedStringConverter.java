package com.jaiz.study.utils;

import javafx.util.StringConverter;

/**
 * 因为很多场景StringConverter的fromString用不上，所以在这里做默认实现
 *
 * @param <T>
 */
public abstract class SimplifiedStringConverter<T> extends StringConverter<T> {
    @Override
    public T fromString(String string) {
        return null;
    }
}
