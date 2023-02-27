package com.nanolaba.wicket.components.form;

import com.nanolaba.wicket.components.Functions;
import com.nanolaba.wicket.components.IFunctionalComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FTextArea<T> extends TextArea<T> implements IFunctionalComponent<FTextArea<T>> {

    private static final long serialVersionUID = -8355122546767454148L;
    private final Functions functions = new Functions();

    public FTextArea(String id) {
        super(id);
    }

    public FTextArea(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    public FTextArea<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FTextArea<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }
}
