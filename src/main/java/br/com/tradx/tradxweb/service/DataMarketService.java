package br.com.tradx.tradxweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;
import br.com.tradx.tradxweb.service.forex.DollarApiService;

@Service
public class DataMarketService {
    
    @Autowired
    private BinanceApiService binanceExchangeService;

    @Autowired
    private MercadoBitcoinApiService mercadoBitcoinExchangeService;

    @Autowired
    private DollarApiService dollarService;

    public double dolarCrypto;

    @Scheduled(fixedRate = 5000)
    public void atualizar() throws Exception{

    }


}
