package br.com.tradx.tradxweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.model.Orderbook;
import br.com.tradx.tradxweb.service.exchange.BinanceExchangeService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinExchangeService;

@Service
public class DataMarketService {
    
    @Autowired
    private BinanceExchangeService binanceExchangeService;

    @Autowired
    private MercadoBitcoinExchangeService mercadoBitcoinExchangeService;

    public double dolarCrypto;

    @Scheduled(fixedRate = 5000)
    public void atualizar(){
        Orderbook orderbookMB = mercadoBitcoinExchangeService.getOrderBook("BTC");
        Orderbook orderbookBNC = binanceExchangeService.getOrderBook("BTCUSDT");
        double vendaMB = mercadoBitcoinExchangeService.getLastAskPrice(orderbookMB);
        double compraBNC = binanceExchangeService.getLastBidPrice(orderbookBNC);

        System.out.println(vendaMB/compraBNC);
        dolarCrypto = vendaMB/compraBNC;
    }


}
