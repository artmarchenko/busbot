package com.atmtrans.busbot.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ShoppingCart {

    private Long routeId;

    private String name;

    private LocalDate voyageDate;

}
