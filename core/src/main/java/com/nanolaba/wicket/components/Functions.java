package com.nanolaba.wicket.components;

import org.apache.wicket.Component;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.io.Serializable;

public class Functions implements Serializable {

    private static final long serialVersionUID = 8850488260301460227L;

    private SerializableSupplier<Boolean> visible;
    private SerializableSupplier<Boolean> enabled;

    public SerializableSupplier<Boolean> getVisible() {
        return visible;
    }

    public void setVisible(SerializableSupplier<Boolean> visible) {
        this.visible = visible;
    }

    public SerializableSupplier<Boolean> getEnabled() {
        return enabled;
    }

    public void setEnabled(SerializableSupplier<Boolean> enabled) {
        this.enabled = enabled;
    }

    public void configure(Component component) {
        if (getVisible() != null) {
            component.setVisible(getVisible().get());
        }
        if (getEnabled() != null) {
            component.setEnabled(component.isEnabled() && getEnabled().get());
        }
    }
}
