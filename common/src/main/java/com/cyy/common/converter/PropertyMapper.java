package com.cyy.common.converter;

import java.util.function.BiConsumer;

public interface PropertyMapper<S, T, R> {
    BeanConverter<S, T> to(BiConsumer<T, R> setter);
}