package com.atmtrans.busbot.conversation.machine.actions;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.conversation.messages.PaymentSuccessful;
import com.atmtrans.busbot.model.ShoppingCart;
import lombok.extern.log4j.Log4j2;
import org.springframework.statemachine.StateContext;


@Log4j2
public class CreateOrderAction extends AbstractPurchaseTicketFlowAction<PaymentSuccessful> {

    public CreateOrderAction() {
        super(PaymentSuccessful.class);
    }

    @Override
    public void execute(StateContext<PurchaseTicketsState, PurchaseTicketsEvent> context) {
        ShoppingCart cart = get(context, ShoppingCart.class);
        PaymentSuccessful msg = getMessage(context);
        log.info("Order created " + cart + " " + msg);
    }
}
