package com.nanolaba.wicket.util;

import org.apache.wicket.model.IDetachable;

public class SelfDetachable implements IDetachable {
    @Override
    public void detach() {
        WicketUtils.detachFields(this);
    }
}
