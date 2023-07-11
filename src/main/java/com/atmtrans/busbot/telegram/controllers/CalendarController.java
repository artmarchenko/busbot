package com.atmtrans.busbot.telegram.controllers;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.atmtrans.busbot.conversation.ConversationEngine;
import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.messages.SelectVoyageDate;
import com.atmtrans.busbot.telegram.util.datepicker.CalendarUtil;
import com.atmtrans.busbot.telegram.views.View;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import lombok.RequiredArgsConstructor;


@BotController
@RequiredArgsConstructor
public class CalendarController extends AbstractTelegramMvcController {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final ConversationEngine conversationEngine;

    @CallbackQueryRequest("calendar_turn_the_month:{date:.*}")
    public BaseRequest turnOver(@BotPathVariable("date") String month, Chat chat, CallbackQuery callback) {
        LocalDate date = LocalDate.parse("01-" + month, DATE_FORMATTER);

        if (date.isAfter(LocalDate.now().withDayOfMonth(1)) || date.isEqual(LocalDate.now().withDayOfMonth(1))) {
            InlineKeyboardMarkup markup = new CalendarUtil().generateKeyboard(date);
            return new EditMessageReplyMarkup(chat.id(), callback.message().messageId()).replyMarkup(markup);
        }
        return null; //TODO get rid of null
    }


    @CallbackQueryRequest("calendar_select_date:{date:.*}")
    public BaseRequest select(@BotPathVariable("date") String date, Chat chat, User user)
        throws Exception {
        DateTimeFormatter localDateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd"); //TODO review static date formatter

        ModelAndView modelAndView =
            conversationEngine.sendMessage(new SelectVoyageDate(user.id(), LocalDate.parse(date, localDateFormatter)));
        View view = resolveView(modelAndView);

        return view.render(chat.id());
    }

    @CallbackQueryRequest("calendar:ignore!@#$%^&")
    public void ignore() {
    }
}
