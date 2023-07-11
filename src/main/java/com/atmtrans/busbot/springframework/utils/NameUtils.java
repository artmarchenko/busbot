package com.atmtrans.busbot.springframework.utils;


import com.google.common.base.CaseFormat;
import org.jetbrains.annotations.NotNull;


public final class NameUtils {

    private NameUtils() {
    }

    @NotNull
    public static String classToBeanName(Class<?> clazz) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName());
    }

}
