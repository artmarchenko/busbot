package com.atmtrans.busbot.model;

import java.math.BigDecimal;
import java.util.Collection;


public record Order(Long id, BigDecimal totalPrice, Collection<Ticket> tickets) {

}
