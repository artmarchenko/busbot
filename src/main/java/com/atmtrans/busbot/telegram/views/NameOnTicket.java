package com.atmtrans.busbot.telegram.views;

import java.io.IOException;
import java.io.StringWriter;

import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.springframework.stereotype.StateNameBasedTelegramView;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.pengrad.telegrambot.request.SendMessage;


@StateNameBasedTelegramView(PurchaseTicketsState.WAIT_NAME)
public class NameOnTicket implements View {

    @Override
    public SendMessage render(Object chatId) throws IOException {
        StringWriter writer = new StringWriter();

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/" + getClass().getSimpleName() + ".mustache");
        mustache.execute(writer, this).flush();

        return new SendMessage(chatId, writer.toString());
    }
}
