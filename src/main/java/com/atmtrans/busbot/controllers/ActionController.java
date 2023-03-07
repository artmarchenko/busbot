package com.atmtrans.busbot.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.atmtrans.busbot.actions.Action;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ActionController implements TelegramBotController {
    private final Action helloAction;

    private final Map<String, Action> actions;

    private final Map<Long, String> dialogPositions = new HashMap<>();

    public ActionController(Collection<Action> actions, @Qualifier("hello") Action hello) {
        this.actions = actions.stream().collect(Collectors.toMap(Action::gatName, Function.identity()));
        this.helloAction = hello;
    }

    @Override
    public String talk(long chatId, String msg) {
        if (dialogPositions.containsKey(chatId)) {
            var actionName = dialogPositions.get(chatId);
            Action action;
            try {
                action = actions.get(actionName).resolveNext(msg);
            } catch (NoSuchElementException e) {
                reset(chatId);
                return "Бувай";
            }
            var reply = action.handle(msg);
            if (action.children().isEmpty()) {
                reset(chatId);
            } else {
                dialogPositions.put(chatId, action.gatName());
            }
            return reply;
        } else {
            String reply = actions.get(helloAction.gatName()).handle(msg);
            dialogPositions.put(chatId, helloAction.gatName());
            return reply;
        }
    }

    @Override
    public void reset(long chatId) {
        dialogPositions.remove(chatId);
    }
}
