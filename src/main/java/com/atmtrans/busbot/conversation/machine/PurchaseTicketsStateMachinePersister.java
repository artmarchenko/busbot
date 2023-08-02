package com.atmtrans.busbot.conversation.machine;

import java.util.HashMap;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;


public class PurchaseTicketsStateMachinePersister
    implements StateMachinePersist<PurchaseTicketsState, PurchaseTicketsEvent, Long> {

    private final HashMap<Long, StateMachineContext<PurchaseTicketsState, PurchaseTicketsEvent>> contexts =
        new HashMap<>();

    @Override
    public void write(final StateMachineContext<PurchaseTicketsState, PurchaseTicketsEvent> context, Long userId) {
        contexts.put(userId, context);
    }

    @Override
    public StateMachineContext<PurchaseTicketsState, PurchaseTicketsEvent> read(Long userId) {
        return contexts.get(userId);
    }
}
