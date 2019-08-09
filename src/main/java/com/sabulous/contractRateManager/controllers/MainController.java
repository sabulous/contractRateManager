package com.sabulous.contractRateManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/", "/home"})
    public String getHomePage() {
        return "contracts";
    }

    @GetMapping(value={"/login"})
    public String getLoginPage() {
        return "login";
    }
}