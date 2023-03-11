package br.com.tradx.tradxweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.model.Exchange;
import br.com.tradx.tradxweb.model.Symbol;
import br.com.tradx.tradxweb.repository.SymbolRepository;
import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.ExchangeService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;
import br.com.tradx.tradxweb.service.forex.DollarApiService;
import jakarta.annotation.PostConstruct;

@Service
public class DataMarketService {

    @Autowired
    private MercadoBitcoinApiService mercadoBitcoinApiService;

    @Autowired
    private BinanceApiService binanceApiService;

    @Autowired
    private DollarApiService dollarApiService;

    @Autowired
    private SymbolRepository symbolRepository;

    private final List<SymbolDTO> symbolsIntersection = new ArrayList<>();

    @PostConstruct
    private void updateMarketDatabase() throws Exception {

        List<SymbolDTO> mbSymbolDTOs = mercadoBitcoinApiService.getAllSymbols();
        List<SymbolDTO> binanceSymbolDTOs = mercadoBitcoinApiService.getAllSymbols();

        for (SymbolDTO mbSymbolDTO : mbSymbolDTOs) {
            for (SymbolDTO binanceSymbolDTO : binanceSymbolDTOs) {
                if (mbSymbolDTO.getBaseCurrency().equals(binanceSymbolDTO.getBaseCurrency())){
                    symbolsIntersection.add(mbSymbolDTO);
                    continue;
                }
            }
        }
        System.out.println(symbolsIntersection);
    }

}
