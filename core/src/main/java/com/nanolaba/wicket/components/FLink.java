package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;


public class FLink<T> extends Link<T> implements IFunctionalComponent<FLink<T>> {

    private static final long serialVersionUID = -358211214066948957L;
    private Action clickAction;
    private final Functions functions = new Functions();

    public FLink(String id) {
        super(id);
    }

    public FLink(String id, IModel<T> model) {
        super(id, model);
    }

    public FLink(String id, Action clickAction) {
        super(id);
        this.clickAction = clickAction;
    }

    public FLink(String id, IModel<T> model, Action clickAction) {
        super(id, model);
        this.clickAction = clickAction;
    }


    public FLink<T> setClickAction(Action clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    @Override
    public FLink<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FLink<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }

    @Override
    public void onClick() {
        if (clickAction != null) {
            clickAction.makeAction();
        }
    }
}
