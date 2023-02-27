package com.nanolaba.wicket.util;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

public class WicketUtils {

    public static <T> T get(IModel<T> model) {
        return get(model, null);
    }

    public static void detachFields(Object... objects) {
        try {
            for (Object oo : objects) {
                for (Field field : oo.getClass().getDeclaredFields()) {
                    field.setAccessible(true);

                    Object o = field.get(oo);
                    if (o instanceof IDetachable) {
                        ((IDetachable) o).detach();
                    } else if (o instanceof Iterable) {
                        for (Object value : (Iterable) o) {
                            if (value instanceof IDetachable) {
                                ((IDetachable) value).detach();
                            }
                        }
                    } else if (o instanceof Map) {
                        for (Object value : ((Map) o).values()) {
                            if (value instanceof IDetachable) {
                                ((IDetachable) value).detach();
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void detach(IDetachable... models) {
        if (models != null) {
            for (IDetachable model : models) {
                if (model != null) {
                    model.detach();
                }
            }
        }
    }

    public static void detach(Iterable<? extends IDetachable> models) {
        if (models != null) {
            for (IDetachable model : models) {
                if (model != null) {
                    model.detach();
                }
            }
        }
    }

    public static <T> T get(IModel<T> model, T defaultValue) {
        if (model == null) {
            return defaultValue;
        }
        T object = model.getObject();
        return object == null ? defaultValue : object;
    }

    public static <T> void expandTree(AbstractTree<T> tree) {
        tree.getModelObject().addAll(toCollection(tree.getProvider(), tree.getProvider().getRoots()));
    }

    public static <T> void expandTree(AbstractTree<T> tree, Object node) {
        tree.getModelObject().addAll(toCollection(tree.getProvider(), List.of(node).iterator()));
    }

    private static Collection toCollection(ITreeProvider provider, Iterator iterator) {
        Collection<Object> res = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            res.add(next);
            res.addAll(toCollection(provider, provider.getChildren(next)));
        }
        return res;
    }

    public static HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) RequestCycle.get().getResponse().getContainerResponse();
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
    }

    public static HttpSession getHttpSession() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest()).getSession(true);
    }

    public static void sendPermanentRedirect(String url) {
        HttpServletResponse response = getHttpServletResponse();
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", url);
    }


    public static <T> T getRequestAttribute(String name, Supplier<T> supplier) {
        HttpServletRequest request = getHttpServletRequest();
        T value = (T) request.getAttribute(name);
        if (value == null) {
            synchronized (request) {
                if (request.getAttribute(name) == null) {
                    value = supplier.get();
                    request.setAttribute(name, value);
                }
            }
        }
        return value;
    }

    public static <T> T getCurrentFieldValue(FormComponent<T> component) {
        if (component != null) {
            T convertedInput = component.getConvertedInput();
            return convertedInput != null ? convertedInput : component.getModelObject();
        }

        return null;
    }

    public static void refreshOnFeedbackUpdate(IEvent<?> event, Component component) {
        Object payload = event.getPayload();
        if (payload instanceof IPartialPageRequestHandler) {
            IPartialPageRequestHandler target = (IPartialPageRequestHandler) payload;
            if (target.getComponents().stream().anyMatch(e -> e instanceof FeedbackPanel)) {
                target.add(component);
            }
        }
    }
}
