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

    private List<TriangleArbitrage> arbitragePairsCombinations = new ArrayList<>();

    private List<TriangleArbitrage> arbitragePairsProfitables = new ArrayList<>();

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
                        arbitragePairsCombinations.add(new TriangleArbitrage(symbol1Buy, symbol2Buy, symbol3Sell));

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
    private void generateArbitragePairsProfitables() {

        List<Ticker> allTickers = binanceApiService.getAllTickers();

        if (arbitragePairsCombinations != null && arbitragePairsCombinations.size() != 0 &&
                allTickers != null && allTickers.size() != 0) {

            arbitragePairsProfitables = arbitragePairsCombinations.parallelStream().map(triangleArbitrage -> {

                allTickers.parallelStream().forEach(ticker -> {
                    if (ticker.getPair().equals(triangleArbitrage.getSymbol1Buy().getName())) {
                        triangleArbitrage.setSymbol1BuyTicker(ticker);
                    } else if (ticker.getPair().equals(triangleArbitrage.getSymbol2Buy().getName())) {
                        triangleArbitrage.setSymbol2BuyTicker(ticker);
                    } else if (ticker.getPair().equals(triangleArbitrage.getSymbol3Sell().getName())) {
                        triangleArbitrage.setSymbol3SellTicker(ticker);
                    }

                    if (triangleArbitrage.getSymbol1Buy() != null &&
                            triangleArbitrage.getSymbol2Buy() != null &&
                            triangleArbitrage.getSymbol3Sell() != null) {
                        return;
                    }

                });

                return triangleArbitrage;

            }).toList();//.filter(triangleArbitrage -> triangleArbitrage.getProfit() > 0.0)
        }
    }

    public List<TriangleArbitrage> getArbitragePairsProfitables() {
        return arbitragePairsProfitables;
    }

}