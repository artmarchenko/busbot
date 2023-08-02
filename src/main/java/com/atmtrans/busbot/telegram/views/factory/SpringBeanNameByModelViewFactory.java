package com.atmtrans.busbot.telegram.views.factory;

import java.util.Map;

import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.springframework.stereotype.ModelNameBasedTelegramView;
import com.atmtrans.busbot.telegram.views.View;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SpringBeanNameByModelViewFactory implements ViewFactory {

    private final ApplicationContext context;


    @Override
    public View view(ModelAndView mv) {
        if (mv.hasNoModel()) {
            return null;
        }

        String beanName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, mv.getModel().getClass().getSimpleName());
        Map<String, Object> beans = context.getBeansWithAnnotation(ModelNameBasedTelegramView.class);
        //        if (!beans.containsKey(beanName)) {
        //            throw new IllegalArgumentException(model.getClass().getName());
        //        }
        View view = (View) beans.get(beanName);
        view.setModel(mv.getModel());
        return view;
    }
}
