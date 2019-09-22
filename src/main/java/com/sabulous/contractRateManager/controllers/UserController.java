package com.sabulous.contractRateManager.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sabulous.contractRateManager.model.Role;
import com.sabulous.contractRateManager.model.User;
import com.sabulous.contractRateManager.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());        
        model.addAttribute("user", new User());       
        return "users";
    }

    @PostMapping("users/add")
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }

    @DeleteMapping("users/delete/{id}")
    public @ResponseBody String deleteUser(@ModelAttribute("user") User user,  @RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    // "removeRole" parameter contains index of a item that will be removed.
    @PostMapping(params = "removeRole", path = {"/users", "/users/{id}"})
    public String removeRole(User user, @RequestParam("removeRole") int index, HttpServletRequest request) {
        List<Role> updatedRoleList = user.getRoles();
        updatedRoleList.remove(index);
        user.setRoles(updatedRoleList);

        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "users::#roles";
        } else {
            return "users";
        }
    }

}