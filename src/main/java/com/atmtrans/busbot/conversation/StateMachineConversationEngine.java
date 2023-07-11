package com.atmtrans.busbot.conversation;

import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import com.atmtrans.busbot.conversation.messages.AbstractConversationMessage;
import com.atmtrans.busbot.conversation.model.Model;
import com.atmtrans.busbot.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class StateMachineConversationEngine implements ConversationEngine {

    private final StateMachineFactory<RoutePurchaseState, RoutePurchaseEvent> stateMachineFactory;

    private final StateMachinePersister<RoutePurchaseState, RoutePurchaseEvent, Long> persister;

    @Override
    public ModelAndView sendMessage(AbstractConversationMessage msg) throws Exception {

        var newMachine = stateMachineFactory.getStateMachine();
        var machine = persister.restore(newMachine, msg.getUserId());

        machine.getExtendedState().getVariables().remove(Model.class.getName());


        machine.getExtendedState().getVariables().put(msg.getClass().getName(), msg);
        machine.sendEvent(msg.getEvent());

        log.debug("STATE: " + machine.getState());
        log.debug("Context: " + machine.getExtendedState().get(ShoppingCart.class, ShoppingCart.class));


        RoutePurchaseState state = machine.getState().getId();
        Object model = machine.getExtendedState().get(Model.class.getName(), Object.class);

        if (null != model) {
            machine.getExtendedState().getVariables().remove(Model.class.getName());
        }

        persister.persist(machine, msg.getUserId());

        return new ModelAndView(state, model);
    }
}
