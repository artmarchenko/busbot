package com.atmtrans.busbot.conversation.messages;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;


public interface ConversationMessage {

    PurchaseTicketsEvent getEvent();
}
