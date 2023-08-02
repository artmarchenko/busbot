package com.atmtrans.busbot.telegram.views;

import java.io.IOException;

import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;


public interface View {

    default void setModel(Object model) {
    }

    <R extends BaseResponse, T extends BaseRequest<T, R>> BaseRequest<T, R> render(Object chatId) throws IOException;

}
