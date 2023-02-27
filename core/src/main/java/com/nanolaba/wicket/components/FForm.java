package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;


public class FForm<T> extends Form<T> implements IComponentWithVisibilityFunction<FForm<T>> {

    private Action submitAction;
    private Action errorAction;
    private SerializableBooleanSupplier visibilityFunction;

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

    @Override
    public FForm<T> setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
        this.visibilityFunction = visibilityFunction;
        return this;
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
    protected void onConfigure() {
        if (visibilityFunction != null) {
            setVisible(visibilityFunction.getAsBoolean());
        }
        super.onConfigure();
    }

    @Override
    protected void onSubmit() {
        if (submitAction != null) {
            submitAction.makeAction();
        }
    }
}
