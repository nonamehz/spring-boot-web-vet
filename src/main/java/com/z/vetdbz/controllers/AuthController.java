package com.z.vetdbz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("usuario")
@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String loginAuth(Model model, @RequestParam("user") String user, @RequestParam("pass") String pass) {

        if (user.equals("admin") && pass.equals("123456")) {

            return "redirect:index";

        }

        model.addAttribute("error", "Denied");

        return "pages/login";

    }

}
