package com.nanolaba.wicket.components;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FCheckBox extends CheckBox implements IFunctionalComponent<FCheckBox> {

    private static final long serialVersionUID = -6607293448487123173L;
    private final Functions functions = new Functions();

    public FCheckBox(String id) {
        super(id);
    }

    public FCheckBox(String id, IModel<Boolean> model) {
        super(id, model);
    }

    @Override
    public FCheckBox setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FCheckBox setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }
}
