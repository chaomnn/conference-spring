package com.example.conferenceweb.democonf.controllers;

import com.example.conferenceweb.democonf.model.User;
import com.example.conferenceweb.democonf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("userForm", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {

        if (userService.saveUser(userForm) == null){
            model.addAttribute("usernameError",
                    "Пользователь с таким именем уже существует");
            return "registration";
        }

        userService.saveUser(userForm);
        return "redirect:/";
    }

}
