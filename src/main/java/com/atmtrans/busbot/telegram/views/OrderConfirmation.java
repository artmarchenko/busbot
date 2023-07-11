package com.atmtrans.busbot.telegram.views;

import java.io.IOException;

import com.atmtrans.busbot.conversation.machine.states.RoutePurchaseState;
import com.atmtrans.busbot.springframework.stereotype.StateNameBasedTelegramView;
import com.pengrad.telegrambot.request.SendMessage;


@StateNameBasedTelegramView(RoutePurchaseState.COMPLETE)
public class OrderConfirmation extends AbstractMustacheView {

    @Override
    public SendMessage render(Object chatId) throws IOException {
        return new SendMessage(chatId, renderTemplate(null));
    }

}
