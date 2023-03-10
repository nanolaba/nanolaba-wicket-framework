package com.nanolaba.wicket.components.form;

import com.nanolaba.wicket.components.Functions;
import com.nanolaba.wicket.components.IFunctionalComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FTextField<T> extends TextField<T> implements IFunctionalComponent<FTextField<T>> {

    private static final long serialVersionUID = -4874600005697441690L;

    private final Functions functions = new Functions();

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
    public FTextField<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FTextField<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }
}
