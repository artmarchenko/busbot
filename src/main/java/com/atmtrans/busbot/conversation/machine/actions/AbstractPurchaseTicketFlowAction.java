package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.conversation.messages.ConversationMessage;


public abstract class AbstractPurchaseTicketFlowAction<M extends ConversationMessage>
    extends BaseAbstractAction<PurchaseTicketsState, PurchaseTicketsEvent, M> {

    protected AbstractPurchaseTicketFlowAction(Class<M> msgType) {
        super(msgType);
    }

}
