package com.nanolaba.wicket.components.form;

import com.nanolaba.wicket.components.IComponentWithVisibilityFunction;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public class FTextField<T> extends TextField<T> implements IComponentWithVisibilityFunction {

    private SerializableBooleanSupplier visibilityFunction;

    public FTextField(String id) {
        super(id);
    }

    public FTextField(String id, Class<T> type) {
        super(id, type);
    }

    public FTextField(String id, IModel<T> model) {
        super(id, model);
    }

    public FTextField(String id, IModel<T> model, Class<T> type) {
        super(id, model, type);
    }

    @Override
    public FTextField setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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
