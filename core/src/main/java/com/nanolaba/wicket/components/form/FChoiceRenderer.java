package com.nanolaba.wicket.components.form;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableFunction;

import java.util.List;

public class FChoiceRenderer<T> implements IChoiceRenderer<T> {

    private static final long serialVersionUID = 9100020706995808457L;
    private SerializableFunction<T, Object> displayValueFunction;
    private SerializableFunction<T, String> idFunction;

    public FChoiceRenderer() {
    }

    public FChoiceRenderer(SerializableFunction<T, Object> displayValueFunction) {
        this.displayValueFunction = displayValueFunction;
    }

    public FChoiceRenderer(SerializableFunction<T, Object> displayValueFunction, SerializableFunction<T, String> idFunction) {
        this.displayValueFunction = displayValueFunction;
        this.idFunction = idFunction;
    }

    public FChoiceRenderer<T> setDisplayValueFunction(SerializableFunction<T, Object> displayValueFunction) {
        this.displayValueFunction = displayValueFunction;
        return this;
    }

    public FChoiceRenderer<T> setIdFunction(SerializableFunction<T, String> idFunction) {
        this.idFunction = idFunction;
        return this;
    }

    @Override
    public Object getDisplayValue(T object) {

        if (object == null) {
            return "";
        }

        return displayValueFunction == null ? object : displayValueFunction.apply(object);
    }

    @Override
    public String getIdValue(T object, int index) {
        if (object == null) {
            return "";
        }

        if (object.getClass().isEnum()) {
            return ((Enum) object).name();
        }

        return idFunction == null ? Integer.toString(index) : idFunction.apply(object);
    }

    @Override
    public T getObject(String id, IModel<? extends List<? extends T>> choices) {
        List<? extends T> _choices = choices.getObject();
        for (int index = 0; index < _choices.size(); index++) {
            T choice = _choices.get(index);
            if (getIdValue(choice, index).equals(id)) {
                return choice;
            }
        }
        return null;
    }
}
