package com.atmtrans.busbot.conversation.machine;

import java.util.HashMap;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;


public class PurchaseRouteStateMachinePersister
    implements StateMachinePersist<RoutePurchaseState, RoutePurchaseEvent, Long> {

    private final HashMap<Long, StateMachineContext<RoutePurchaseState, RoutePurchaseEvent>> contexts = new HashMap<>();

    @Override
    public void write(final StateMachineContext<RoutePurchaseState, RoutePurchaseEvent> context, Long userId) {
        contexts.put(userId, context);
    }

    @Override
    public StateMachineContext<RoutePurchaseState, RoutePurchaseEvent> read(Long userId) {
        return contexts.get(userId);
    }
}
