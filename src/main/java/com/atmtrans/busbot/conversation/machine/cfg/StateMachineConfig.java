package com.atmtrans.busbot.conversation.machine.cfg;


import java.util.EnumSet;

import com.atmtrans.busbot.conversation.machine.PurchaseTicketsStateMachinePersister;
import com.atmtrans.busbot.conversation.machine.actions.CollectNameOnTicket;
import com.atmtrans.busbot.conversation.machine.actions.CollectVoyageDate;
import com.atmtrans.busbot.conversation.machine.actions.CreateOrderAction;
import com.atmtrans.busbot.conversation.machine.actions.SelectRouteToPurchase;
import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;


@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<PurchaseTicketsState, PurchaseTicketsEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<PurchaseTicketsState, PurchaseTicketsEvent> config)
        throws Exception {
        config
            .withConfiguration()
            .autoStartup(true);
    }

    @Override
    public void configure(final StateMachineStateConfigurer<PurchaseTicketsState, PurchaseTicketsEvent> states)
        throws Exception {
        states
            .withStates()
            .initial(PurchaseTicketsState.INIT)
            .end(PurchaseTicketsState.COMPLETE)
            .states(EnumSet.allOf(PurchaseTicketsState.class));
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<PurchaseTicketsState, PurchaseTicketsEvent> transitions)
        throws Exception {

        transitions
            .withExternal()
            .source(PurchaseTicketsState.INIT)
            .target(PurchaseTicketsState.WAIT_NAME)
            .event(PurchaseTicketsEvent.ROUTE_SELECTED)
            .action(new SelectRouteToPurchase());

        transitions
            .withExternal()
            .source(PurchaseTicketsState.WAIT_NAME)
            .target(PurchaseTicketsState.WAIT_DATE)
            .event(PurchaseTicketsEvent.TEXTED)
            .action(new CollectNameOnTicket());

        transitions
            .withExternal()
            .source(PurchaseTicketsState.WAIT_DATE)
            .target(PurchaseTicketsState.WAIT_INVOICE)
            .event(PurchaseTicketsEvent.VOYAGE_DATE_SELECTED)
            .action(new CollectVoyageDate());

        transitions
            .withExternal()
            .source(PurchaseTicketsState.WAIT_INVOICE)
            .target(PurchaseTicketsState.COMPLETE)
            .event(PurchaseTicketsEvent.PAYED)
            .action(new CreateOrderAction());
    }

    @Bean
    public StateMachinePersister<PurchaseTicketsState, PurchaseTicketsEvent, Long> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseTicketsStateMachinePersister());
    }
}
