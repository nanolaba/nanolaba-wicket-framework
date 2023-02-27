package com.nanolaba.wicket.interfaces;

import org.apache.wicket.ajax.AjaxRequestTarget;

import java.io.Serializable;

@FunctionalInterface
public interface AjaxErrorAction extends Serializable {

    void makeAction(AjaxRequestTarget target, RuntimeException e);
}
