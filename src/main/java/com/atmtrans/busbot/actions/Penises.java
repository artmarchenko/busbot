package com.atmtrans.busbot.actions;

import java.util.Collection;

public record Penises(Collection<Action> children) implements Action {
    @Override
    public String handle(String msg) {
        return "Прикре самогубство";
    }

    @Override
    public boolean canHandle(String msg) {
        return msg.matches("хуи драченые") || msg.equals("b");
    }
}
