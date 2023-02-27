package com.nanolaba.wicket.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

import java.io.Serializable;

public class NoBodyLabel extends Label implements IComponentWithVisibilityFunction<NoBodyLabel> {

    private SerializableBooleanSupplier visibilityFunction;

    public NoBodyLabel(String id) {
        super(id);
        setRenderBodyOnly(true);
    }

    public NoBodyLabel(String id, Serializable label) {
        super(id, label);
        setRenderBodyOnly(true);
    }

    public NoBodyLabel(String id, IModel<?> model) {
        super(id, model);
        setRenderBodyOnly(true);
    }

    @Override
    protected void onConfigure() {
        if (visibilityFunction != null) {
            setVisible(visibilityFunction.getAsBoolean());
        }
        super.onConfigure();
    }

    @Override
    public NoBodyLabel setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
        this.visibilityFunction = visibilityFunction;
        return this;
    }
}
