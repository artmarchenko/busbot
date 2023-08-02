package com.atmtrans.busbot.telegram.views;

import java.io.IOException;
import java.time.LocalDate;

import com.atmtrans.busbot.springframework.stereotype.ModelNameBasedTelegramView;
import com.atmtrans.busbot.telegram.util.datepicker.CalendarUtil;
import com.pengrad.telegrambot.request.SendMessage;


@ModelNameBasedTelegramView
public class DatePicker implements View {

    private LocalDate month;

    @Override
    public void setModel(Object model) {
        month = ((com.atmtrans.busbot.conversation.model.DatePicker) model).month();
    }

    @Override
    public SendMessage render(Object chatId) throws IOException {
        return new SendMessage(chatId, "Calendar").replyMarkup(new CalendarUtil().generateKeyboard(month));
    }
}
