package br.com.arbify.arbifyweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.arbify.arbifyweb.service.DataMarketService;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";
    }
}
