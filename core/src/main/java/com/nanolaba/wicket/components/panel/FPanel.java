package com.nanolaba.wicket.components.panel;

import com.nanolaba.wicket.components.IComponentWithVisibilityFunction;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FPanel<T> extends GenericPanel<T> implements IComponentWithVisibilityFunction<FPanel> {

    private SerializableBooleanSupplier visibilityFunction;
    private Set<IModel> attachedModels = new HashSet<>();

    public FPanel(String id) {
        super(id);
    }

    public FPanel(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    public FPanel setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
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
