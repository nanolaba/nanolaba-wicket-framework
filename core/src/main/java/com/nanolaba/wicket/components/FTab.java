package com.nanolaba.wicket.components;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.io.Serializable;


public class FTab<T extends WebMarkupContainer> extends AbstractTab implements IFunctionalComponent<FTab<T>> {

    private static final long serialVersionUID = 6526762649559208326L;
    private PanelSupplier<T> panelSupplier;
    private final Functions functions = new Functions();

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
    public FTab<T> setFunctionVisible(SerializableSupplier<Boolean> function) {
        functions.setVisible(function);
        return this;
    }

    @Override
    public FTab<T> setFunctionEnabled(SerializableSupplier<Boolean> function) {
        functions.setEnabled(function);
        return this;
    }

    @Override
    public WebMarkupContainer getPanel(String id) {
        return panelSupplier == null ? null : panelSupplier.get(id);
    }

    @Override
    public boolean isVisible() {
        return functions.getVisible() == null ? super.isVisible() : functions.getVisible().get();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public interface PanelSupplier<T extends WebMarkupContainer> extends Serializable {
        T get(String panelId);
    }

}
