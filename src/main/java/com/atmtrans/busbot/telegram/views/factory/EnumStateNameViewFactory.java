package com.atmtrans.busbot.telegram.views.factory;

import java.util.Collection;

import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.springframework.stereotype.StateNameBasedTelegramView;
import com.atmtrans.busbot.telegram.views.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EnumStateNameViewFactory implements ViewFactory {

    @Autowired
    private ApplicationContext context;


    public View view(ModelAndView mv) {
        if (mv.hasNoName()) {
            return null;
        }
        Collection<Object> beans = context.getBeansWithAnnotation(StateNameBasedTelegramView.class).values();

        View view = null;

        for (Object bean : beans) {
            StateNameBasedTelegramView a = bean.getClass().getAnnotation(StateNameBasedTelegramView.class);
            if (a.value().name().equals(mv.getName())) {
                view = (View) bean;
            }
        }

        return view;
    }
}
