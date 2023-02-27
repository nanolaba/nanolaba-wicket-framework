package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FSubmitLink extends SubmitLink implements IFunctionalComponent<FSubmitLink> {

    private static final long serialVersionUID = 200275494451899658L;
    private Action submitAction;
    private Action afterSubmitAction;
    private Action errorAction;

    private final Functions functions = new Functions();

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
    public FSubmitLink setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FSubmitLink setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
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
