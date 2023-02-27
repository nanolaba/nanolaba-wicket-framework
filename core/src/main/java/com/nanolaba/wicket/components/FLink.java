package com.nanolaba.wicket.components;

import com.nanolaba.wicket.interfaces.Action;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;


public class FLink<T> extends Link<T> implements IComponentWithVisibilityFunction<FLink<T>> {

    private Action clickAction;
    private SerializableBooleanSupplier visibilityFunction;

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

    @Override
    public FLink<T> setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
        this.visibilityFunction = visibilityFunction;
        return this;
    }

    public FLink<T> setClickAction(Action clickAction) {
        this.clickAction = clickAction;
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
    public void onClick() {
        if (clickAction != null) {
            clickAction.makeAction();
        }
    }
}
