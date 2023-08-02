package com.atmtrans.busbot.telegram.views.factory;

import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.telegram.views.View;


public interface ViewFactory {

    View view(ModelAndView mv);
}
