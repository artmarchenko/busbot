package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class SelectRoute extends AbstractConversationMessage {

    private final RoutePurchaseEvent event = RoutePurchaseEvent.ROUTE_SELECTED;

    private final Long routeId;

    public SelectRoute(long userId, Long routeId) {
        super(userId);
        this.routeId = routeId;
    }
}
