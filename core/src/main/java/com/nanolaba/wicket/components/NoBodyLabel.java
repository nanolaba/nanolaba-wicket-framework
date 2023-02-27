package com.nanolaba.wicket.components;

import com.nanolaba.wicket.components.basic.FLabel;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

public class NoBodyLabel extends FLabel {

    private static final long serialVersionUID = 2908427965868192301L;

    public NoBodyLabel(String id) {
        super(id);
        setRenderBodyOnly(true);
    }

    public NoBodyLabel(String id, Serializable label) {
        super(id, label);
        setRenderBodyOnly(true);
    }

    public NoBodyLabel(String id, IModel<?> model) {
        super(id, model);
        setRenderBodyOnly(true);
    }
}
