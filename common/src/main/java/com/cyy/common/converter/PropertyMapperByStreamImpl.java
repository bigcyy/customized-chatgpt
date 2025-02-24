package com.cyy.common.converter;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PropertyMapperByStreamImpl<S, T, R> implements PropertyMapper<S, T, R> {
    private final BeanConverter<S, T> converter;
    private final Optional<R> nullAbleValue;

    public PropertyMapperByStreamImpl(BeanConverter<S, T> converter, Optional<R> nullAbleValue) {
        this.converter = converter;
        this.nullAbleValue = nullAbleValue;
    }


    public <R2> PropertyMapperByStreamImpl<S,T,R2> mapping(Function<R, R2> mapper){
        Optional<R2> nullAbleMappingValue = nullAbleValue.map(mapper);
        return new PropertyMapperByStreamImpl<>(this.converter, nullAbleMappingValue);
    }
    /**
     * 配置参数映射后的赋值函数
     * @param setter 赋值函数
     * @return bean 转换器
     */
    public BeanConverter<S, T> to(BiConsumer<T, R> setter) {
        PropertyConverter<S, T> customPropertyConverter = (p, target) -> {
            nullAbleValue.ifPresent(mappingValue -> setter.accept(target, mappingValue));
            return target;
        };
        this.converter.withCustomConverter(customPropertyConverter);
        return converter;
    }
}