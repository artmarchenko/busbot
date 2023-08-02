package com.atmtrans.busbot.services;

import java.util.List;

import com.atmtrans.busbot.model.RoutePair;


public interface RouteService {

    List<RoutePair> findAllRoutes();

}
