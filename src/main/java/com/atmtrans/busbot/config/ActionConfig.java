package com.atmtrans.busbot.config;

import com.atmtrans.busbot.actions.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class ActionConfig {

    @Bean
    public Action javelin() {
        return new Javelin(Collections.emptyList());
    }

    @Bean
    public Action penises() {
        return new Penises(Collections.emptyList());
    }

    @Bean
    public Action penis() {
        return new Penis(Collections.emptyList());
    }

    @Bean
    public Action orc(@Qualifier("javelin") Action javelin, @Qualifier("penises") Action penises, @Qualifier("penis") Action penis) {
        return new Orc(List.of(javelin, penises, penis));
    }

    @Bean
    public Action notOrc() {
        return new NotOrc(Collections.emptyList());
    }

    @Bean
    public Action artem() {
        return new Artem(Collections.emptyList());
    }

    @Bean
    public Action hello(@Qualifier("orc") Action orc, @Qualifier("notOrc") Action notOrc, @Qualifier("artem") Action artem) {
        return new Hello(List.of(orc, notOrc, artem));
    }
}
