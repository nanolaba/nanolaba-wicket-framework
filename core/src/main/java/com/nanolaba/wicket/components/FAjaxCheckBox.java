package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.AjaxAction;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

public class FAjaxCheckBox extends AjaxCheckBox implements IComponentWithVisibilityFunction<FAjaxCheckBox> {

    private AjaxAction updateAction;
    private SerializableBooleanSupplier visibilityFunction;

    public FAjaxCheckBox(String id) {
        super(id);
    }

    public FAjaxCheckBox(String id, IModel<Boolean> model) {
        super(id, model);
    }

    public FAjaxCheckBox(String id, AjaxAction updateAction) {
        super(id);
        this.updateAction = updateAction;
    }

    public FAjaxCheckBox(String id, IModel<Boolean> model, AjaxAction updateAction) {
        super(id, model);
        this.updateAction = updateAction;
    }

    public FAjaxCheckBox setUpdateAction(AjaxAction updateAction) {
        this.updateAction = updateAction;
        return this;
    }

    @Override
    public FAjaxCheckBox setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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
    protected void onUpdate(AjaxRequestTarget target) {
        if (updateAction != null) {
            updateAction.makeAction(target);
        }
    }
}
