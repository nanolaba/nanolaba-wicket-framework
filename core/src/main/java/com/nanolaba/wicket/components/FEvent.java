package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.AjaxAction;
import com.nanolaba.wicket.interfaces.AjaxErrorAction;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;


public class FEvent extends AjaxFormComponentUpdatingBehavior {

    private AjaxAction updateAction;
    private AjaxErrorAction errorAction;

    public FEvent(String event) {
        super(event);
    }

    public FEvent setUpdateAction(AjaxAction updateAction) {
        this.updateAction = updateAction;
        return this;
    }

    public FEvent setErrorAction(AjaxErrorAction errorAction) {
        this.errorAction = errorAction;
        return this;
    }

    @Override
    protected void onUpdate(AjaxRequestTarget target) {
        if (updateAction != null) {
            updateAction.makeAction(target);
        }
    }

    @Override
    protected void onError(AjaxRequestTarget target, RuntimeException e) {
        if (errorAction != null) {
            errorAction.makeAction(target, e);
        } else {
            super.onError(target, e);
        }
    }
}
