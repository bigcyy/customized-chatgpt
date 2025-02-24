package com.cyy.common.converter;

@FunctionalInterface
public interface PropertyConverter<S, T> {
    T convert(S source, T target);
}