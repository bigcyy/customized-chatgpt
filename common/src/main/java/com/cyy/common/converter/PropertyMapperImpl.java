package com.cyy.common.converter;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 参数映射规则实现类
 * @param <S> 源对象
 * @param <T> 目标对象
 */
public class PropertyMapperImpl<S, T, R> implements PropertyMapper<S, T, R> {
    private final BeanConverter<S, T> converter;
    private final Function<S, R> getter;

    public PropertyMapperImpl(BeanConverter<S, T> converter, Function<S, R> getter) {
        this.converter = converter;
        this.getter = getter;
    }

    /**
     * 配置参数映射后的赋值函数
     * @param setter 赋值函数
     * @return bean 转换器
     */
    public BeanConverter<S, T> to(BiConsumer<T, R> setter) {
        PropertyConverter<S, T> customPropertyConverter = new SimplePropertyConverter<>(getter, setter);
        this.converter.withCustomConverter(customPropertyConverter);
        return converter;
    }
}