package com.atmtrans.busbot.actions;

import java.util.Collection;

public record Javelin(Collection<Action> children) implements Action {
    @Override
    public String handle(String msg) {
        return "Яке прикре самогубство";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("пики точеные") || msg.equals("a");
    }
}
