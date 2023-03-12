package br.com.arbify.arbifyweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.arbify.arbifyweb.model.Symbol;
import br.com.arbify.arbifyweb.model.Ticker;
import br.com.arbify.arbifyweb.model.TriangleArbitrage;
import jakarta.annotation.PostConstruct;

@Service
public class DataMarketService {

    @Autowired
    private BinanceApiService binanceApiService;

    private List<TriangleArbitrage> arbitragePairsCombination = new ArrayList<>();

    private int maxRetries = 3;

    @PostConstruct
    private void generateArbitragePairsCombinations() throws InterruptedException {
        try {
            List<Symbol> binanceSymbols = binanceApiService.getAllSymbols();
            List<Symbol> symbol1BuyList = binanceSymbols.parallelStream()
                    .filter(symbol -> symbol.getCurrency().equals("USDT")).toList();

            for (Symbol symbol1Buy : symbol1BuyList) {
                List<Symbol> symbol2BuyList = binanceSymbols.parallelStream()
                        .filter(symbol -> symbol.getCurrency().equals(symbol1Buy.getBaseCurrency())).toList();

                for (Symbol symbol2Buy : symbol2BuyList) {
                    Symbol symbol3Sell = symbol1BuyList.parallelStream()
                            .filter(symbol -> symbol.getBaseCurrency().equals(symbol2Buy.getBaseCurrency()))
                            .findFirst().orElse(null);

                    if (symbol3Sell != null) {
                        arbitragePairsCombination.add(new TriangleArbitrage(symbol1Buy, symbol2Buy, symbol3Sell));

                    }
                }
            }
        } catch (Exception e) {
            retryGenerateArbitragePairsCombinations();
        }
    }

    private void retryGenerateArbitragePairsCombinations() throws InterruptedException {
        if (maxRetries != 0) {
            maxRetries--;
            System.out.println();
            System.out.println(maxRetries);
            System.out.println();
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    generateArbitragePairsCombinations();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.err.println("Erro na execução do método " +
                    "\"generateArbitragePairsCombinations()\", " +
                    "favor reiniciar a aplicação.");
        }
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    private void metodo() {
        if (arbitragePairsCombination != null && arbitragePairsCombination.size() != 0) {
            List<Ticker> allTickers = binanceApiService.getAllTickers();

            arbitragePairsCombination = arbitragePairsCombination.parallelStream().map(triangleArbitrage -> {
                triangleArbitrage.setSymbol1BuyTicker(allTickers.parallelStream()
                        .filter(ticker -> ticker.getPair().equals(triangleArbitrage.getSymbol1Buy().getName()))
                        .findFirst().orElseThrow());

                triangleArbitrage.setSymbol2BuyTicker(allTickers.parallelStream()
                        .filter(ticker -> ticker.getPair().equals(triangleArbitrage.getSymbol2Buy().getName()))
                        .findFirst().orElseThrow());

                triangleArbitrage.setSymbol3SellTicker(allTickers.parallelStream()
                        .filter(ticker -> ticker.getPair().equals(triangleArbitrage.getSymbol3Sell().getName()))
                        .findFirst().orElseThrow());

                return triangleArbitrage;
            }).toList();
            System.out.println(arbitragePairsCombination);
        }
    }
}