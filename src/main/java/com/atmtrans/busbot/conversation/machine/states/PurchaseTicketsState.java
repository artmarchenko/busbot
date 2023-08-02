package com.atmtrans.busbot.conversation.machine.states;

public enum PurchaseTicketsState implements State {

    WAIT_NAME,
    WAIT_DATE,
    WAIT_INVOICE,

    INIT,
    COMPLETE
}
