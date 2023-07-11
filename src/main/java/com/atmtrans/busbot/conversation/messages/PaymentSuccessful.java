package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class PaymentSuccessful extends AbstractConversationMessage {

    private RoutePurchaseEvent event = RoutePurchaseEvent.PAYED;

    private Long routeId;

    public PaymentSuccessful(long userId) {
        super(userId);
    }
}
