package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.AjaxAction;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class FAjaxCheckBox extends AjaxCheckBox implements IFunctionalComponent<FAjaxCheckBox> {

    private static final long serialVersionUID = 7722042939708164042L;
    private AjaxAction updateAction;
    private final Functions functions = new Functions();

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
    public FAjaxCheckBox setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FAjaxCheckBox setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }

    @Override
    protected void onUpdate(AjaxRequestTarget target) {
        if (updateAction != null) {
            updateAction.makeAction(target);
        }
    }
}
