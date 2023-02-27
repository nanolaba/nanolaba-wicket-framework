package com.nanolaba.wicket.components;

import org.danekja.java.util.function.serializable.SerializableSupplier;

public interface IFunctionalComponent<T> {

    T setFunctionVisible(SerializableSupplier<Boolean> function);

    T setFunctionEnabled(SerializableSupplier<Boolean> function);
}
