package com.gmail.programmerfromearth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                        Model model) {
        model.addAttribute("name", name);
        return "home";
    }
}
