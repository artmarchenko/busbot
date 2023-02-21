package com.atmtrans.busbot.actions;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class NotOrc implements Action {
    private final Collection<Action> children;

    @Override
    public String handle(String msg) {
        return "Проходь дальше файно людино";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("не москаль");
    }

    @Override
    public Collection<Action> children() {
        return children;
    }
}
