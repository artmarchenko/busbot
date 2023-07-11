package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import com.atmtrans.busbot.conversation.messages.SelectRoute;
import com.atmtrans.busbot.model.ShoppingCart;
import org.springframework.statemachine.StateContext;


public class SelectRouteToPurchase extends AbstractPurchaseTicketFlowAction<SelectRoute> {

    public SelectRouteToPurchase() {
        super(SelectRoute.class);
    }

    @Override
    public void execute(StateContext<RoutePurchaseState, RoutePurchaseEvent> context) {
        ShoppingCart cart = new ShoppingCart();
        SelectRoute msg = getMessage(context);
        cart.setRouteId(msg.getRouteId());
        put(context, cart);
    }
}
