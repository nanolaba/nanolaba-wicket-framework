package com.nanolaba.wicket.components.basic;

import com.nanolaba.wicket.components.Functions;
import com.nanolaba.wicket.components.IFunctionalComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.io.Serializable;

public class FLabel extends Label implements IFunctionalComponent<FLabel> {

    private static final long serialVersionUID = 6755928287477538138L;
    private final Functions functions = new Functions();

    public FLabel(String id) {
        super(id);
    }

    public FLabel(String id, Serializable label) {
        super(id, label);
    }

    public FLabel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    public FLabel setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FLabel setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }
}

