package com.nanolaba.wicket.components.basic;

import com.nanolaba.wicket.components.IComponentWithVisibilityFunction;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

import java.io.Serializable;

public class FLabel extends Label implements IComponentWithVisibilityFunction<FLabel> {

    private SerializableBooleanSupplier visibilityFunction;

    public FLabel(String id) {
        super(id);
    }

    public FLabel(String id, Serializable label) {
        super(id, label);
    }

    public FLabel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    public FLabel setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
        this.visibilityFunction = visibilityFunction;
        return this;
    }

    @Override
    protected void onConfigure() {
        if (visibilityFunction != null) {
            setVisible(visibilityFunction.getAsBoolean());
        }
        super.onConfigure();
    }
}

