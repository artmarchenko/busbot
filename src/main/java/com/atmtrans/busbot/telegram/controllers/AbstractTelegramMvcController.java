package com.atmtrans.busbot.telegram.controllers;

import java.util.List;
import java.util.Objects;

import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.telegram.views.View;
import com.atmtrans.busbot.telegram.views.factory.ViewFactory;
import com.github.kshashov.telegram.api.TelegramMvcController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public abstract class AbstractTelegramMvcController implements TelegramMvcController {

    @Autowired
    private List<ViewFactory> viewFactories;

    @Getter
    @Value("${bot.token}")
    protected String token;

    protected View resolveView(ModelAndView mv) {
        return viewFactories.stream()
            .map(factory -> factory.view(mv)).filter(Objects::nonNull).findFirst()
            .orElseThrow();
    }
}
