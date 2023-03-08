package com.atmtrans.busbot.actions;

import java.util.Collection;


public record Penis(Collection<Action> children) implements Action {

    @Override
    public String handle(String msg) {
        return "хароший выбор";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("за щоку") || msg.equals("c");
    }
}
