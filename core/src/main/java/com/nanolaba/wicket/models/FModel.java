package com.nanolaba.wicket.models;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.LoadableDetachableModel;
import org.danekja.java.util.function.serializable.SerializableSupplier;

import java.util.ArrayList;
import java.util.List;

public class FModel<P> extends LoadableDetachableModel<P> {

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
    protected void onDetach() {
        objectForDetach.forEach(e -> e.detach());
        super.onDetach();
    }

    @Override
    protected P load() {
        return getter.get();
    }
}