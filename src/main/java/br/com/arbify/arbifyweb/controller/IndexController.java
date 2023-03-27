package br.com.arbify.arbifyweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ModelAndView("private/index")
                    .addObject("authentication", authentication);
        }
        return new ModelAndView("public/index");
    }

}
