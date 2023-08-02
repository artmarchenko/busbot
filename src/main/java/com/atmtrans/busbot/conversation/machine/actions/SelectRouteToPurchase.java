package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.conversation.messages.SelectRoute;
import com.atmtrans.busbot.model.ShoppingCart;
import org.springframework.statemachine.StateContext;


public class SelectRouteToPurchase extends AbstractPurchaseTicketFlowAction<SelectRoute> {

    public SelectRouteToPurchase() {
        super(SelectRoute.class);
    }

    @Override
    public void execute(StateContext<PurchaseTicketsState, PurchaseTicketsEvent> context) {
        ShoppingCart cart = new ShoppingCart();
        SelectRoute msg = getMessage(context);
        cart.setRouteId(msg.getRouteId());
        put(context, cart);
    }
}
