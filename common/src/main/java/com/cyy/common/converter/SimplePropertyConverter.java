package com.cyy.common.converter;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 传入两个函数，Getter 函数用于从源对象获取要转换的参数值。
 * Setter 函数用于将得到的参数值设置到目标对象的参数中。
 * @param <S> 表示待转换的源对象（source）
 * @param <T> 表示要转换成的目标对象（target）
 */
public class SimplePropertyConverter<S, T, R> implements PropertyConverter<S, T> {

    private final Function<S, R> getter;
    private final BiConsumer<T, R> setter;

    public SimplePropertyConverter(Function<S,R> getter, BiConsumer<T, R> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public T convert(S source, T target) {
        R value = getter.apply(source);
        setter.accept(target, value);
        return target;
    }
}
