package de.goldmann.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MapAppController
{

    @RequestMapping("/reports")
    public String reports()
    {
        return "reports/index";
    }

    @RequestMapping("/editor")
    public String editor()
    {
        return "editor/index";
    }

}
