package com.sabulous.contractRateManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/", "/hello"})
    public String getContractsPage() {
        return "hello";
    }

    @GetMapping(value={"/login"})
    public String getLoginPage() {
        System.out.println("loginpage GET request");
        return "login";
    }

}