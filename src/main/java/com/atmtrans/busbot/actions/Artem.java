package com.atmtrans.busbot.actions;

import java.util.Collection;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Artem implements Action {

    private final Collection<Action> children;

    @Override
    public String handle(String msg) {
        return "Прівет Артьом";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("Артьом");
    }

    @Override
    public Collection<Action> children() {
        return children;
    }
}
