package com.nanolaba.wicket.models;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.LoadableDetachableModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.util.ArrayList;
import java.util.List;

public class FModel<P> extends LoadableDetachableModel<P> {

    private static final long serialVersionUID = 3802385558748145518L;

    private final SerializableSupplier<P> getter;
    private final List<IDetachable> objectForDetach = new ArrayList<>();

    public FModel(SerializableSupplier<P> getter) {
        this.getter = getter;
    }

    public static <P> FModel<P> of(IDetachable detachable, SerializableSupplier<P> getter) {
        return new FModel<>(getter).addDetachable(detachable);
    }

    public FModel<P> addDetachable(IDetachable... detachables) {
        if (detachables != null) {
            for (IDetachable detachable : detachables) {
                if (detachable != null) {
                    objectForDetach.add(detachable);
                }
            }
        }

        return this;
    }

    @Override
    public void detach() {
        objectForDetach.forEach(IDetachable::detach);
        super.detach();
    }

    @Override
    protected P load() {
        return getter.get();
    }
}