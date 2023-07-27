package com.atmtrans.busbot.conversation;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
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

    private final StateMachineFactory<PurchaseTicketsState, PurchaseTicketsEvent> stateMachineFactory;

    private final StateMachinePersister<PurchaseTicketsState, PurchaseTicketsEvent, Long> persister;

    @Override
    public ModelAndView sendMessage(AbstractConversationMessage msg) throws Exception {

        var newMachine = stateMachineFactory.getStateMachine();
        var machine = persister.restore(newMachine, msg.getUserId());

        machine.getExtendedState().getVariables().remove(Model.class.getName());


        machine.getExtendedState().getVariables().put(msg.getClass().getName(), msg);
        machine.sendEvent(msg.getEvent());

        log.debug("STATE: " + machine.getState());
        log.debug("Context: " + machine.getExtendedState().get(ShoppingCart.class, ShoppingCart.class));


        PurchaseTicketsState state = machine.getState().getId();
        Object model = machine.getExtendedState().get(Model.class.getName(), Object.class);

        if (null != model) {
            machine.getExtendedState().getVariables().remove(Model.class.getName());
        }

        persister.persist(machine, msg.getUserId());

        return new ModelAndView(state, model);
    }
}
