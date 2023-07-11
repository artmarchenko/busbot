package com.atmtrans.busbot.conversation.machine.cfg;


import java.util.EnumSet;

import com.atmtrans.busbot.conversation.machine.PurchaseRouteStateMachinePersister;
import com.atmtrans.busbot.conversation.machine.actions.CollectNameOnTicket;
import com.atmtrans.busbot.conversation.machine.actions.CollectVoyageDate;
import com.atmtrans.busbot.conversation.machine.actions.CreateOrderAction;
import com.atmtrans.busbot.conversation.machine.actions.SelectRouteToPurchase;
import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
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
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<RoutePurchaseState, RoutePurchaseEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<RoutePurchaseState, RoutePurchaseEvent> config)
        throws Exception {
        config
            .withConfiguration()
            .autoStartup(true);
    }

    @Override
    public void configure(final StateMachineStateConfigurer<RoutePurchaseState, RoutePurchaseEvent> states)
        throws Exception {
        states
            .withStates()
            .initial(RoutePurchaseState.INIT)
            .end(RoutePurchaseState.COMPLETE)
            .states(EnumSet.allOf(RoutePurchaseState.class));
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<RoutePurchaseState, RoutePurchaseEvent> transitions)
        throws Exception {

        transitions
            .withExternal()
            .source(RoutePurchaseState.INIT)
            .target(RoutePurchaseState.WAIT_NAME)
            .event(RoutePurchaseEvent.ROUTE_SELECTED)
            .action(new SelectRouteToPurchase());

        transitions
            .withExternal()
            .source(RoutePurchaseState.WAIT_NAME)
            .target(RoutePurchaseState.WAIT_DATE)
            .event(RoutePurchaseEvent.TEXTED)
            .action(new CollectNameOnTicket());

        transitions
            .withExternal()
            .source(RoutePurchaseState.WAIT_DATE)
            .target(RoutePurchaseState.WAIT_INVOICE)
            .event(RoutePurchaseEvent.VOYAGE_DATE_SELECTED)
            .action(new CollectVoyageDate());

        transitions
            .withExternal()
            .source(RoutePurchaseState.WAIT_INVOICE)
            .target(RoutePurchaseState.COMPLETE)
            .event(RoutePurchaseEvent.PAYED)
            .action(new CreateOrderAction());
    }

    @Bean
    public StateMachinePersister<RoutePurchaseState, RoutePurchaseEvent, Long> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseRouteStateMachinePersister());
    }
}
