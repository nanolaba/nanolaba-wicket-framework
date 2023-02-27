package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public class FSubmitLink extends SubmitLink implements IComponentWithVisibilityFunction<FSubmitLink> {

    private Action submitAction;
    private Action afterSubmitAction;
    private Action errorAction;
    private SerializableBooleanSupplier visibilityFunction;


    public FSubmitLink(String id) {
        super(id);
    }

    public FSubmitLink(String id, Form<?> form) {
        super(id, form);
    }

    public FSubmitLink(String id, IModel<?> model) {
        super(id, model);
    }

    public FSubmitLink(String id, IModel<?> model, Form<?> form) {
        super(id, model, form);
    }

    public FSubmitLink setSubmitAction(Action submitAction) {
        this.submitAction = submitAction;
        return this;
    }

    public FSubmitLink setAfterSubmitAction(Action afterSubmitAction) {
        this.afterSubmitAction = afterSubmitAction;
        return this;
    }

    public FSubmitLink setErrorAction(Action errorAction) {
        this.errorAction = errorAction;
        return this;
    }

    @Override
    public FSubmitLink setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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

    @Override
    public void onSubmit() {
        if (submitAction != null) {
            submitAction.makeAction();
        }
    }

    @Override
    public void onError() {
        if (errorAction != null) {
            errorAction.makeAction();
        }
    }

    @Override
    public void onAfterSubmit() {
        if (afterSubmitAction != null) {
            afterSubmitAction.makeAction();
        }
    }
}
