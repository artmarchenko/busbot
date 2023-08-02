package com.atmtrans.busbot.telegram.controllers;


import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.PreCheckoutQueryRequest;
import com.pengrad.telegrambot.model.PreCheckoutQuery;
import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery;
import lombok.RequiredArgsConstructor;


@BotController
@RequiredArgsConstructor
public class InvoiceController extends AbstractTelegramMvcController {

    @PreCheckoutQueryRequest
    public AnswerPreCheckoutQuery approve(PreCheckoutQuery query) {
        return new AnswerPreCheckoutQuery(query.id());
    }

}
