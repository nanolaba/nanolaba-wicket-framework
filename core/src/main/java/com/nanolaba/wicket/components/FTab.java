package com.nanolaba.wicket.components;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.java.util.function.serializable.SerializableBooleanSupplier;

import java.io.Serializable;


public class FTab<T extends WebMarkupContainer> extends AbstractTab implements IComponentWithVisibilityFunction<FTab<T>> {

    private PanelSupplier<T> panelSupplier;
    private SerializableBooleanSupplier visibilityFunction;

    public FTab(String title, PanelSupplier<T> panelSupplier) {
        this(Model.of(title), panelSupplier);
    }

    public FTab(IModel<String> title, PanelSupplier<T> panelSupplier) {
        super(title);
        this.panelSupplier = panelSupplier;
    }

    public FTab<T> setPanelSupplier(PanelSupplier<T> panelSupplier) {
        this.panelSupplier = panelSupplier;
        return this;
    }

    @Override
    public FTab<T> setVisibilityFunction(SerializableBooleanSupplier visibilityFunction) {
        this.visibilityFunction = visibilityFunction;
        return this;
    }

    @Override
    public WebMarkupContainer getPanel(String id) {
        return panelSupplier == null ? null : panelSupplier.get(id);
    }

    @Override
    public boolean isVisible() {
        return visibilityFunction == null ? super.isVisible() : visibilityFunction.getAsBoolean();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public interface PanelSupplier<T extends WebMarkupContainer> extends Serializable {
        T get(String panelId);
    }

}
