package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class SelectRoute extends AbstractConversationMessage {

    private static final @Getter PurchaseTicketsEvent event = PurchaseTicketsEvent.ROUTE_SELECTED;

    private final Long routeId;

    public SelectRoute(long userId, Long routeId) {
        super(userId);
        this.routeId = routeId;
    }
}
