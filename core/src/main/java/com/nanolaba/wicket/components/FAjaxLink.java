package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.AjaxAction;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;


public class FAjaxLink<T> extends AjaxLink<T> implements IFunctionalComponent<FAjaxLink<T>> {

    private static final long serialVersionUID = 7594872527846098585L;

    private AjaxAction clickAction;
    private final Functions functions = new Functions();

    public FAjaxLink(String id) {
        super(id);
    }

    public FAjaxLink(String id, IModel<T> model) {
        super(id, model);
    }

    public FAjaxLink(String id, AjaxAction clickAction) {
        super(id);
        this.clickAction = clickAction;
    }

    public FAjaxLink(String id, IModel<T> model, AjaxAction clickAction) {
        super(id, model);
        this.clickAction = clickAction;
    }

    public FAjaxLink<T> setClickAction(AjaxAction clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    @Override
    public FAjaxLink<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FAjaxLink<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
        if (clickAction != null) {
            clickAction.makeAction(target);
        }
    }
}
