package com.atmtrans.busbot.actions;

import java.util.Collection;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Orc implements Action {

    private final Collection<Action> children;

    @Override
    public String handle(String msg) {
        return "Маєш три варианти: a) пики точеные b) хуи дрочоные c) за щоку";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("москаль");
    }

    @Override
    public Collection<Action> children() {
        return children;
    }
}
