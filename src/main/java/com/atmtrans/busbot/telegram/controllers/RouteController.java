package com.atmtrans.busbot.telegram.controllers;


import java.io.IOException;

import com.atmtrans.busbot.conversation.ConversationEngine;
import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.messages.SelectRoute;
import com.atmtrans.busbot.services.RouteService;
import com.atmtrans.busbot.telegram.views.RoutePicker;
import com.atmtrans.busbot.telegram.views.View;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;


@BotController
@RequiredArgsConstructor
public class RouteController extends AbstractTelegramMvcController {

    private final RouteService routeService;

    private final ConversationEngine conversator;

    @MessageRequest("/showroutes")
    public SendMessage listRoutes(User user) throws IOException {
        return new RoutePicker(routeService.findAllRoutes()).render(user.id());
    }

    //TODO generics
    @CallbackQueryRequest("select_route_to_buy:{routeId:.*}")
    public BaseRequest selectRouteToBuy(@BotPathVariable("routeId") Long routeId, Chat chat, User user)
        throws Exception {

        ModelAndView mv = conversator.sendMessage(new SelectRoute(user.id(), routeId));
        View view = resolveView(mv);

        return view.render(chat.id());
    }
}
