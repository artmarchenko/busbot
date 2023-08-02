package com.atmtrans.busbot.springframework.stereotype;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope("prototype")
public @interface StateNameBasedTelegramView {

    PurchaseTicketsState value();
}
