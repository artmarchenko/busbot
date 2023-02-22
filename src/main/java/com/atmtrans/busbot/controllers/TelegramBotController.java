package com.atmtrans.busbot.controllers;

public interface TelegramBotController {
    String talk(long chatId, String msg);
    void reset(long chatId);
}
