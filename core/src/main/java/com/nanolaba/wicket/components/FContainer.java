package com.nanolaba.wicket.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FContainer extends WebMarkupContainer implements IFunctionalComponent<FContainer> {

    private static final long serialVersionUID = -5948127797227129377L;
    private final Functions functions = new Functions();

    public FContainer(String id) {
        super(id);
    }

    public FContainer(String id, SerializableSupplier<Boolean> visibilityFunction, boolean renderBodyOnly) {
        this(id, null, visibilityFunction, renderBodyOnly);
    }

    public FContainer(String id, SerializableSupplier<Boolean> visibilityFunction) {
        this(id, visibilityFunction, false);
    }

    public FContainer(String id, IModel<?> model, SerializableSupplier<Boolean> visibilityFunction) {
        this(id, model, visibilityFunction, false);
    }

    public FContainer(String id, IModel<?> model, SerializableSupplier<Boolean> visibilityFunction, boolean renderBodyOnly) {
        super(id, model);
        functions.setVisible(visibilityFunction);
        setRenderBodyOnly(renderBodyOnly);
    }

    @Override
    public FContainer setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FContainer setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }
}
