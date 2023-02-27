package com.nanolaba.wicket.interfaces;

import java.io.Serializable;

@FunctionalInterface
public interface Action extends Serializable {

    void makeAction();
}
