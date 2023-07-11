package com.atmtrans.busbot.telegram.views;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import com.atmtrans.busbot.model.RoutePair;
import com.atmtrans.busbot.springframework.stereotype.ModelNameBasedTelegramView;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;


@ModelNameBasedTelegramView
public class RoutePicker {

    public RoutePicker(Collection<RoutePair> routes) {
        this.routes = routes.stream()
            .map(routePair -> new InternalRoutePair(
                routePair.straight().id().toString(), routePair.straight().name(),
                routePair.reverse().id().toString(), routePair.reverse().name())
            )
            .toList();
    }


    Collection<InternalRoutePair> routes;


    public SendMessage render(long chatId) throws IOException {
        StringWriter writer = new StringWriter();

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/" + getClass().getSimpleName() + ".mustache");
        mustache.execute(writer, this).flush();


        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();


        for (InternalRoutePair route : routes) {
            keyboardMarkup.addRow(
                new InlineKeyboardButton(route.straight).callbackData("select_route_to_buy:" + route.sid),
                new InlineKeyboardButton(route.reverse).callbackData("select_route_to_buy:" + route.rid)
            );
        }

        SendMessage message = new SendMessage(chatId, writer.toString());
        message.replyMarkup(keyboardMarkup);

        return message;
    }


    public record InternalRoutePair(String sid, String straight, String rid, String reverse) {

    }
}
