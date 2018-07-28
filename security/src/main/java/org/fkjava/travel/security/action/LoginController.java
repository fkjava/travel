package org.fkjava.travel.security.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("security/login")
public class LoginController {
    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("security/login");

        return mav;
    }
}
