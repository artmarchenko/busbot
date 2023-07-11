package com.atmtrans.busbot.model;

import java.time.LocalDate;


public record Ticket(String name, LocalDate date, String route) {

}
