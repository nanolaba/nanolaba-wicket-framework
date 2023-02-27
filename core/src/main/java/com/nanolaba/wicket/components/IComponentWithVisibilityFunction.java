package com.nanolaba.wicket.components;

import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public interface IComponentWithVisibilityFunction<T> {

    T setVisibilityFunction(SerializableBooleanSupplier visibilityFunction);
}
