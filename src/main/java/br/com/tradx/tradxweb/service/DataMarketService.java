package br.com.tradx.tradxweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.DollarApiService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;

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
        OrderbookDTO orderbookMB = mercadoBitcoinExchangeService.getOrderBook("BTC");
        OrderbookDTO orderbookBNC = binanceExchangeService.getOrderBook("BTCUSDT");
        double vendaMB = mercadoBitcoinExchangeService.getLastAskPrice(orderbookMB);
        double compraBNC = binanceExchangeService.getLastBidPrice(orderbookBNC);

        System.out.println(vendaMB/compraBNC);
        dolarCrypto = vendaMB/compraBNC;
    }


}
