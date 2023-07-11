package com.atmtrans.busbot.conversation.messages;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;


public interface ConversationMessage {

    RoutePurchaseEvent getEvent();
}
