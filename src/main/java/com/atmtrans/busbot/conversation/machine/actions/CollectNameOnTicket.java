package com.atmtrans.busbot.conversation.machine.actions;

import java.time.LocalDate;
import java.util.Objects;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.conversation.messages.Text;
import com.atmtrans.busbot.conversation.model.DatePicker;
import com.atmtrans.busbot.model.ShoppingCart;
import org.springframework.statemachine.StateContext;


public class CollectNameOnTicket extends AbstractPurchaseTicketFlowAction<Text> {

    public CollectNameOnTicket() {
        super(Text.class);
    }

    @Override
    public void execute(StateContext<PurchaseTicketsState, PurchaseTicketsEvent> context) {
        ShoppingCart cart = get(context, ShoppingCart.class);
        Text msg = getMessage(context);

        Objects.requireNonNull(msg.getText());

        cart.setName(msg.getText());

        putModel(context, new DatePicker(LocalDate.now()));
    }
}
