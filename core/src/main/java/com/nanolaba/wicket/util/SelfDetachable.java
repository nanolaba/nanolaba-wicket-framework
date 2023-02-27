package com.nanolaba.wicket.util;

import org.apache.wicket.model.IDetachable;

public class SelfDetachable implements IDetachable {
    private static final long serialVersionUID = -125490301829137410L;

    @Override
    public void detach() {
        WicketUtils.detachFields(this);
    }
}
