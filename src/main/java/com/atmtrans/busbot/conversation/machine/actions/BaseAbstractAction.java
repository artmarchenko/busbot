package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.messages.ConversationMessage;
import com.atmtrans.busbot.conversation.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;


@RequiredArgsConstructor
public abstract class BaseAbstractAction<S, E, M extends ConversationMessage> implements Action<S, E> {

    private final Class<M> msgType;

    protected <V> void put(StateContext<S, E> context, V value) {
        context.getExtendedState().getVariables().put(value.getClass().getName(), value);
    }

    protected <T> T get(StateContext<S, E> context, Class<T> key) {
        return context.getExtendedState().get(key.getName(), key);
    }

    protected M getMessage(StateContext<S, E> context) {
        M msg = context.getExtendedState().get(msgType.getName(), msgType);
        context.getExtendedState().getVariables().remove(msgType.getName());
        return msg;
    }

    protected void putModel(StateContext<S, E> context, Model model) {
        context.getExtendedState().getVariables().put(Model.class.getName(), model);
    }

}
