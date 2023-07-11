package com.atmtrans.busbot.conversation.machine.mv;

import com.atmtrans.busbot.conversation.machine.states.State;
import lombok.Getter;


@Getter
public class ModelAndView {

    String name;

    Object model;

    public ModelAndView(Object model) {
        this.model = model;
    }

    public ModelAndView(State view) {
        this.name = view.name();
    }

    public ModelAndView(State view, Object model) {
        this.name = view.name();
        this.model = model;
    }

    public boolean hasNoName() {
        return null == name;
    }

    public boolean hasNoModel() {
        return null == model;
    }
}
