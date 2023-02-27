package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;


public class FForm<T> extends Form<T> implements IFunctionalComponent<FForm<T>> {

    private static final long serialVersionUID = 2130976320228462749L;
    private Action submitAction;
    private Action errorAction;
    private final Functions functions = new Functions();

    public FForm(String id) {
        super(id);
    }

    public FForm(String id, IModel<T> model) {
        super(id, model);
    }

    public FForm(String id, Action submitAction) {
        super(id);
        this.submitAction = submitAction;
    }

    public FForm<T> setSubmitAction(Action submitAction) {
        this.submitAction = submitAction;
        return this;
    }

    public FForm<T> setErrorAction(Action errorAction) {
        this.errorAction = errorAction;
        return this;
    }

    @Override
    protected void onError() {
        if (errorAction != null) {
            errorAction.makeAction();
        }
    }

    @Override
    public FForm<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FForm<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }

    @Override
    protected void onSubmit() {
        if (submitAction != null) {
            submitAction.makeAction();
        }
    }
}
