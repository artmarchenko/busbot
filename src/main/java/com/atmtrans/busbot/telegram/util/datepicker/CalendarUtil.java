package com.atmtrans.busbot.telegram.util.datepicker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;


public class CalendarUtil {

    public static final String IGNORE = "calendar:ignore!@#$%^&";

    private static final String[] WEEK_DAYS = {"M", "T", "W", "T", "F", "S", "S"};


    public InlineKeyboardMarkup generateKeyboard(LocalDate date) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        for (List<InlineKeyboardButton> row : generateKeys(date)) {
            keyboardMarkup.addRow(
                row.toArray(
                    new InlineKeyboardButton[row.size()]
                )
            );
        }
        return keyboardMarkup;
    }

    public List<List<InlineKeyboardButton>> generateKeys(LocalDate date) {

        if (date == null) {
            return null;
        }

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // row - Month and Year
        List<InlineKeyboardButton> headerRow = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        String text = date.format(formatter);

        headerRow.add(createButton(IGNORE, text));
        keyboard.add(headerRow);

        // row - Days of the week
        List<InlineKeyboardButton> daysOfWeekRow = new ArrayList<>();

        for (String day : WEEK_DAYS) {
            daysOfWeekRow.add(createButton(IGNORE, day));
        }
        keyboard.add(daysOfWeekRow);

        LocalDate firstDay = LocalDate.of(date.getYear(), date.getMonth(), 1);
        int shift = firstDay.getDayOfWeek().getValue() - 1;
        int daysInMonth = firstDay.lengthOfMonth();
        int rows = ((daysInMonth + shift) % 7 > 0 ? 1 : 0) + (daysInMonth + shift) / 7;

        for (int i = 0; i < rows; i++) {
            keyboard.add(buildRow(firstDay, shift));
            firstDay = firstDay.plusDays(7L - shift);
            shift = 0;
        }

        List<InlineKeyboardButton> controlsRow = new ArrayList<>();

        LocalDate nextMonth = date.plusMonths(1);
        LocalDate prevMonth = date.minusMonths(1);

        DateTimeFormatter buttonFormatter = DateTimeFormatter.ofPattern("MM-yyyy");

        controlsRow.add(createButton("calendar_turn_the_month:" + prevMonth.format(buttonFormatter), "<"));
        controlsRow.add(createButton("calendar_turn_the_month:" + nextMonth.format(buttonFormatter), ">"));
        keyboard.add(controlsRow);

        return keyboard;
    }

    private InlineKeyboardButton createButton(String callBack, String text) {
        return new InlineKeyboardButton(text).callbackData(callBack);
    }

    private List<InlineKeyboardButton> buildRow(LocalDate date, int shift) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        int day = date.getDayOfMonth();
        LocalDate callbackDate = date;

        for (int j = 0; j < shift; j++) {
            row.add(createButton(IGNORE, " "));
        }

        for (int j = shift; j < 7; j++) {

            if (day <= (date.lengthOfMonth())) {
                row.add(createButton("calendar_select_date:" + callbackDate.toString(),
                    Integer.toString(day++) + System.lineSeparator() + "⁴²"));
                callbackDate = callbackDate.plusDays(1);
            } else {
                row.add(createButton(IGNORE, " "));
            }
        }
        return row;
    }
}
