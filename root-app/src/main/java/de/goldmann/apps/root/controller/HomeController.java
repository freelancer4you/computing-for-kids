package de.goldmann.apps.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Enthält Methoden die von der Index-Seite benutzt benutzt werden.
 * 
 * @author goldmana
 *
 */
@Controller
public class HomeController
{
    /**
     * Routing auf die Indexseite.
     * 
     * @return
     */
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }


}
