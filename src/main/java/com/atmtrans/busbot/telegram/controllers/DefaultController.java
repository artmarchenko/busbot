package com.atmtrans.busbot.telegram.controllers;


import com.atmtrans.busbot.conversation.ConversationEngine;
import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.messages.PaymentSuccessful;
import com.atmtrans.busbot.conversation.messages.Text;
import com.atmtrans.busbot.telegram.views.View;
import com.github.kshashov.telegram.api.TelegramRequest;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import lombok.RequiredArgsConstructor;


@BotController
@RequiredArgsConstructor
public class DefaultController extends AbstractTelegramMvcController {

    private final ConversationEngine conversationEngine;

    @MessageRequest
    public BaseRequest defaultMessage(TelegramRequest request, Chat chat, User user) throws Exception {
        ModelAndView modelAndView;

        if (null != request.getMessage().successfulPayment()) {
            modelAndView = conversationEngine.sendMessage(new PaymentSuccessful(user.id()));
        } else {
            modelAndView = conversationEngine.sendMessage(new Text(user.id(), request.getText()));
        }

        View view = resolveView(modelAndView);
        return view.render(chat.id());
    }
}
