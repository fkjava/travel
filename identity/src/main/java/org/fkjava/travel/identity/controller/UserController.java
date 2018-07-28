package org.fkjava.travel.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/identity/user")
public class UserController {

    @GetMapping
    public ModelAndView list() {
        return null;
    }
}
