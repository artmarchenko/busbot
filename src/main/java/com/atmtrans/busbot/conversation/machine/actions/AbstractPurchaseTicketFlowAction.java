package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import com.atmtrans.busbot.conversation.messages.ConversationMessage;


public abstract class AbstractPurchaseTicketFlowAction<M extends ConversationMessage>
    extends BaseAbstractAction<RoutePurchaseState, RoutePurchaseEvent, M> {

    public AbstractPurchaseTicketFlowAction(Class<M> msgType) {
        super(msgType);
    }

}
