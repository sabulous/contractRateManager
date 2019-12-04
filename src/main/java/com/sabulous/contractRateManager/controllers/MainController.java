package com.sabulous.contractRateManager.controllers;

import com.sabulous.contractRateManager.services.IUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/", "/home"})
    public String getHomePage(Authentication authentication, Model model) {
        
        if(authentication == null) {
            return "login";
        }
        return "home";
    }

    @GetMapping(value={"/login"})
    public String getLoginPage(Authentication authentication) {
        if(authentication != null) {
            return "home";
        }
        return "login";
    }

}