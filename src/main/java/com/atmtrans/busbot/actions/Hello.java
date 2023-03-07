package com.atmtrans.busbot.actions;

import java.util.Collection;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Hello implements Action {

    private final Collection<Action> children;


    @Override
    public String handle(String msg) {
        return "Привіт, ти хто?";
    }

    @Override
    public boolean canHandle(String msg) {
        return true;
    }

    @Override
    public Collection<Action> children() {
        return children;
    }
}
