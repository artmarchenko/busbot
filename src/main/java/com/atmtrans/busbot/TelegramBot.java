package com.atmtrans.busbot;

import com.atmtrans.busbot.controllers.TelegramBotController;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.tinylog.Logger;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;

    private final TelegramBotController controller;

    public TelegramBot(@Value("${bot.token}") String token, @Value("${bot.name}") String botName,
                       TelegramBotController controller) throws TelegramApiException {
        super(token);

        this.botName = botName;
        this.controller = controller;

        Logger.info("TOKEN " + token);
        new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Logger.info("Update received");

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/reset")) {
                controller.reset(chatId);
            } else {
                String reply = controller.talk(chatId, messageText);
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText(reply);
                execute(message);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
