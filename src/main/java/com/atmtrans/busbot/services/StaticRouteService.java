package com.atmtrans.busbot.services;

import java.util.List;

import com.atmtrans.busbot.model.Route;
import com.atmtrans.busbot.model.RoutePair;
import com.atmtrans.busbot.model.Stop;
import org.springframework.stereotype.Service;


@Service
public class StaticRouteService implements RouteService {


    private static final List<RoutePair> ROUTES_LIST = List.of(
        new RoutePair(
            new Route(1L, "Ukraine - Moldova", List.of(
                new Stop("Kyiv"),
                new Stop("Uman"),
                new Stop("Chishinau")
            )),
            new Route(2L, "Moldova - Ukraine", List.of(
                new Stop("Chishinau"),
                new Stop("Uman"),
                new Stop("Kyiv")
            ))
        ),
        new RoutePair(
            new Route(3L, "Ukraine - Poland", List.of(
                new Stop("Kyiv"),
                new Stop("Lviv"),
                new Stop("Krakow"))),
            new Route(4L, "Poland - Ukraine", List.of(
                new Stop("Krakow"),
                new Stop("Lviv"),
                new Stop("Kyiv"))
            )
        )
    );

    @Override
    public List<RoutePair> findAllRoutes() {
        return ROUTES_LIST;
    }
}
