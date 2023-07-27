package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class PaymentSuccessful extends AbstractConversationMessage {

    private static final @Getter PurchaseTicketsEvent event = PurchaseTicketsEvent.PAYED;

    private Long routeId;

    public PaymentSuccessful(long userId) {
        super(userId);
    }
}
