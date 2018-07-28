package org.fkjava.travel.security.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("security/register")
public class RegisterController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("security/register");
        
        return mav;
    }
}
