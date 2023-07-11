package com.atmtrans.busbot.conversation.machine.states;

//TODO ticket purchase
public enum RoutePurchaseState implements State {

    WAIT_NAME,
    WAIT_DATE,
    WAIT_INVOICE,

    INIT,
    COMPLETE
}
