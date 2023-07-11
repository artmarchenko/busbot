package com.atmtrans.busbot.model;

import java.util.Collection;


public record Route(Long id, String name, Collection<Stop> stops) {


}
