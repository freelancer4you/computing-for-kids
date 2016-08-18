package de.goldmann.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.goldmann.apps.root.services.VisitorsCounter;

@Controller
public class MapAppController {

    @Autowired
    private VisitorsCounter visitorsCounter;

    @RequestMapping("/visitorsCount")
    public int visitorsCount() {
        return visitorsCounter.getCounter();
    }
}