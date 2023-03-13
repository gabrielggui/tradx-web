package br.com.arbify.arbifyweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arbify.arbifyweb.model.TriangleArbitrage;
import br.com.arbify.arbifyweb.service.DataMarketService;

@RestController
public class TestController {
    
    @Autowired
    private DataMarketService dataMarketService;

    @GetMapping("/api")
    public List<TriangleArbitrage> tes(){
        return dataMarketService.getArbitragePairsProfitables();
    }
}
