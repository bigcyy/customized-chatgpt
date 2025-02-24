package com.cyy.common.converter;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;


/**
 * 对象转换器，可配置对象每个属性的转换规则，也可使用默认的转换规则。
 * 默认会使用 Spring 的 BeanUtils.copyProperties 将名称和类型均相同的属性进行拷贝。若需要取消默认的转换规则，调用 withoutDefaultCopy() 取消。
 * 可以自行配置参数的映射，调用 withCustomConverter(converter)。可以使用最简单的参数映射 {@link SimplePropertyConverter}
 * @param <S> 待转换的源对象
 * @param <T> 目标对象
 */
public class BeanConverter<S, T> {
    private  Class<T> targetClass;
    private  T targetInstance;
    private final S source;
    private final List<PropertyConverter<S, T>> customPropertyConverters = new ArrayList<>();
    private boolean useDefaultCopy = true; // 默认使用 BeanUtils.copyProperties

    public BeanConverter(S source, Class<T> targetClass) {
        this.source = Objects.requireNonNull(source, "Source cannot be null");
        this.targetClass = Objects.requireNonNull(targetClass, "Target class cannot be null");
    }

    public BeanConverter(S source, T targetInstance) {
        this.source = Objects.requireNonNull(source, "Source cannot be null");
        this.targetInstance = Objects.requireNonNull(targetInstance, "Target instance cannot be null");
    }

    /**
     * 不使用 BeanUtils.copyProperties
     */
    public BeanConverter<S, T> withoutDefaultCopy() {
        this.useDefaultCopy = false;
        return this;
    }

    /**
     * 传入源对象并创建一个对象转换器
     * @param source 源对象
     * @param <S> 源对象
     */
    public static <S> SourceStep<S> source(@NotNull S source) {
        return new SourceStep<>(source);
    }

    /**
     * 自定义参数转换器
     * @param propertyConverter
     * @return
     */
    public BeanConverter<S, T> withCustomConverter(PropertyConverter<S, T> propertyConverter) {
        customPropertyConverters.add(propertyConverter);
        return this;
    }

    /**
     * 调用此方法表明使用 {@link SimplePropertyConverter} ,该方法需要传入参数获取函数。
     * @param getter 参数获取函数。
     * @return 参数映射对象，可调用 to 方法配置参数获取后的逻辑。
     */
    public <R> PropertyMapper<S, T, R> map(Function<S, R> getter) {
        return new PropertyMapperImpl<>(this, getter);
    }

    public <R> PropertyMapperByStreamImpl<S, T, R> streamMap(Function<S, R> getter) {
        R value = getter.apply(source);
        Optional<R> nullAbleValue = Optional.ofNullable(value);
        return new PropertyMapperByStreamImpl<>(this, nullAbleValue);
    }

    /**
     * 转换成目标对象
     * 默认会使用 Spring 的 BeanUtils.copyProperties 将名称和类型均相同的属性进行拷贝。若需要取消默认的转换规则，调用 withoutDefaultCopy() 取消。
     * 可以自行配置参数的映射，调用 withCustomConverter(converter)。可以使用最简单的参数映射，调用 map 方法能创建一个 {@link SimplePropertyConverter}。
     *
     * @return
     */
    public T convert() {
        if(targetInstance == null)
            this.targetInstance = BeanUtils.instantiateClass(targetClass);
        if(useDefaultCopy) {
            useBeanUtilsConvertTo(targetInstance);
        }
        usePropertyConverterConvertTo(targetInstance);
        return targetInstance;
    }

    /**
     * 使用默认的 Spring beanUtils 转换
     */
    private void useBeanUtilsConvertTo(T target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 使用定义的参数转换器转换
     */
    private void usePropertyConverterConvertTo(T target){
        for (PropertyConverter<S, T> propertyConverter : customPropertyConverters) {
            propertyConverter.convert(source, target);
        }
    }

    public static class SourceStep<S> {
        private final S source;

        public SourceStep(S source) {
            this.source = source;
        }

        /**
         * 传入目标对象的字节码并创建一个转换到字节码类型的对象转换器
         * @param targetClass 目标对象字节码
         * @return 对象转换器
         * @param <T> 需要转换的目标的类型
         */
        public <T> BeanConverter<S, T> target(@NotNull Class<T> targetClass) {
            return new BeanConverter<>(source, targetClass);
        }

        public <T> BeanConverter<S, T> target(@NotNull T targetInstance) {
            return new BeanConverter<>(source, targetInstance);
        }
    }
}