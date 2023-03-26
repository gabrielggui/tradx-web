package br.com.arbify.arbifyweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.arbify.arbifyweb.service.DataMarketService;
import br.com.arbify.arbifyweb.service.IconService;

@Controller
public class IndexController {

    @Autowired
    private DataMarketService dataMarketService;

    @Autowired
    private IconService iconService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index")
                .addObject("arbitragePairsProfitables", dataMarketService.getArbitragePairsProfitables())
                .addObject("iconService",iconService);
    }
    
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login")
                .addObject("arbitragePairsProfitables", dataMarketService.getArbitragePairsProfitables())
                .addObject("iconService",iconService);
    }

    @GetMapping("tools/arbitrage/triangular")
    public ModelAndView arbitragemTriangular(){
        return new ModelAndView("triangulararbitrage")
        .addObject("arbitragePairsProfitables", dataMarketService.getArbitragePairsProfitables())
        .addObject("iconService",iconService);
    }
}
