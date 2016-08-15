package de.goldmann.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.goldmann.apps.root.services.VisitorsCounter;

@Controller
public class MapAppController {

    @Autowired
    private VisitorsCounter visitorsCounter;

    @RequestMapping("/reports")
    public String reports() {
        return "reports/index";
    }

    @RequestMapping("/editor")
    public String editor() {
        return "editor/index";
    }

	// Request kommen hier an, aber ein Mapping ist nicht möglich
	// "partials/about/index"
	// oder aber "about/index" wird auch nicht gefunden
	@RequestMapping("/index.html{value}")
	public String about(@PathVariable("value") String value) {
		System.out.println(value);
		return "partials/about/index";
	}


    @RequestMapping("/visitorsCount")
    public int visitorsCount() {
        return visitorsCounter.getCounter();
    }
}