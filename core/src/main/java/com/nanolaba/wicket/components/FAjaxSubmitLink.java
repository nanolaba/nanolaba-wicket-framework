package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.AjaxAction;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public class FAjaxSubmitLink extends AjaxSubmitLink implements IComponentWithVisibilityFunction<FAjaxSubmitLink> {

    private AjaxAction submitAction;
    private AjaxAction afterSubmitAction;
    private AjaxAction errorAction;
    private SerializableBooleanSupplier visibilityFunction;

    public FAjaxSubmitLink(String id) {
        super(id);
    }

    public FAjaxSubmitLink(String id, Form<?> form) {
        super(id, form);
    }

    public FAjaxSubmitLink setSubmitAction(AjaxAction submitAction) {
        this.submitAction = submitAction;
        return this;
    }

    public FAjaxSubmitLink setAfterSubmitAction(AjaxAction afterSubmitAction) {
        this.afterSubmitAction = afterSubmitAction;
        return this;
    }

    public FAjaxSubmitLink setErrorAction(AjaxAction errorAction) {
        this.errorAction = errorAction;
        return this;
    }

    @Override
    public FAjaxSubmitLink setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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
    protected void onAfterSubmit(AjaxRequestTarget target) {
        if (afterSubmitAction != null) {
            afterSubmitAction.makeAction(target);
        }
    }

    @Override
    protected void onSubmit(AjaxRequestTarget target) {
        if (submitAction != null) {
            submitAction.makeAction(target);
        }
    }

    @Override
    protected void onError(AjaxRequestTarget target) {
        if (errorAction != null) {
            errorAction.makeAction(target);
        }
    }

}
