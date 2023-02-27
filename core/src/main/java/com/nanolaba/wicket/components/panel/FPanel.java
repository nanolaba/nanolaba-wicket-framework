package com.nanolaba.wicket.components.panel;

import com.nanolaba.wicket.components.Functions;
import com.nanolaba.wicket.components.IFunctionalComponent;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FPanel<T> extends GenericPanel<T> implements IFunctionalComponent<FPanel> {

    private static final long serialVersionUID = -4743851042541413223L;
    private final Functions functions = new Functions();
    private final Set<IModel> attachedModels = new HashSet<>();

    public FPanel(String id) {
        super(id);
    }

    public FPanel(String id, IModel<T> model) {
        super(id, model);
    }


    @Override
    public FPanel<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FPanel<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    protected void onConfigure() {
        functions.configure(this);
        super.onConfigure();
    }

    public <TT> IModel<TT> attachModel(IModel<TT> model) {
        attachedModels.add(model);
        return model;
    }

    public void attachModels(IModel... models) {
        if (models != null) {
            attachedModels.addAll(Arrays.asList(models));
        }
    }

    @Override
    public void detachModels() {
        super.detachModels();
        attachedModels.forEach(e -> {
            if (e != null) {
                e.detach();
            }
        });
    }
}
