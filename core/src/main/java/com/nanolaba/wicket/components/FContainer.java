package com.nanolaba.wicket.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public class FContainer extends WebMarkupContainer implements IComponentWithVisibilityFunction<FContainer> {

    private SerializableBooleanSupplier visibilityFunction;

    public FContainer(String id) {
        super(id);
    }

    public FContainer(String id, SerializableBooleanSupplier visibilityFunction, boolean renderBodyOnly) {
        this(id, null, visibilityFunction, renderBodyOnly);
    }

    public FContainer(String id, SerializableBooleanSupplier visibilityFunction) {
        this(id, visibilityFunction, false);
    }

    public FContainer(String id, IModel<?> model, SerializableBooleanSupplier visibilityFunction) {
        this(id, model, visibilityFunction, false);
    }

    public FContainer(String id, IModel<?> model, SerializableBooleanSupplier visibilityFunction, boolean renderBodyOnly) {
        super(id, model);
        this.visibilityFunction = visibilityFunction;
        setRenderBodyOnly(renderBodyOnly);
    }

    @Override
    public FContainer setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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
