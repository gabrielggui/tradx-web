package br.com.arbify.arbifyweb.controller.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.arbify.arbifyweb.service.DataMarketService;
import br.com.arbify.arbifyweb.service.IconService;

@Controller
@RequestMapping("/criptomoedas")
public class CriptomoedasController {

    @Autowired
    private DataMarketService dataMarketService;

    @Autowired
    private IconService iconService;

    @GetMapping("/arbitragem/triangular")
    public ModelAndView arbitragemTriangular(Authentication authentication) {
        return new ModelAndView("private/triangulararbitrage")
                .addObject("authentication", authentication)
                .addObject("arbitragePairsProfitables", dataMarketService.getArbitragePairsProfitables())
                .addObject("iconService", iconService);
    }
}
