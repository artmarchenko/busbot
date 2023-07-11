package com.atmtrans.busbot.conversation.machine.actions;

import java.util.Objects;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import com.atmtrans.busbot.conversation.messages.SelectVoyageDate;
import com.atmtrans.busbot.model.ShoppingCart;
import org.springframework.statemachine.StateContext;


public class CollectVoyageDate extends AbstractPurchaseTicketFlowAction<SelectVoyageDate> {

    public CollectVoyageDate() {
        super(SelectVoyageDate.class);
    }

    @Override
    public void execute(StateContext<RoutePurchaseState, RoutePurchaseEvent> context) {
        ShoppingCart cart = get(context, ShoppingCart.class);
        SelectVoyageDate msg = getMessage(context);
        Objects.requireNonNull(msg.getDate());
        cart.setVoyageDate(msg.getDate());
    }
}
