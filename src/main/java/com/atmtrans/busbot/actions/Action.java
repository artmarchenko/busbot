package com.atmtrans.busbot.actions;

import java.util.Collection;


public interface Action {

    String handle(String msg);

    boolean canHandle(String msg);

    Collection<Action> children();

    default Action resolveNext(String msg) {
        return children().stream().filter(action -> action.canHandle(msg)).findFirst().orElseThrow();
    }

    default String gatName() {
        return getClass().getSimpleName().toLowerCase();
    }
}
