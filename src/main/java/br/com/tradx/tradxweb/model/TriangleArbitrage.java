package br.com.tradx.tradxweb.model;

import org.springframework.stereotype.Component;

@Component
public class TriangleArbitrage {
    private Symbol symbol1Buy;
    private Symbol symbol2Buy;
    private Symbol symbol3Sell;
    private Ticker symbol1BuyTicker;
    private Ticker symbol2BuyTicker;
    private Ticker symbol3SellTicker;

    public TriangleArbitrage() {
    }

    public TriangleArbitrage(Symbol symbol1Buy, Symbol symbol2Buy, Symbol symbol3Sell) {
        this.symbol1Buy = symbol1Buy;
        this.symbol2Buy = symbol2Buy;
        this.symbol3Sell = symbol3Sell;
    }

    public TriangleArbitrage(Symbol symbol1Buy, Symbol symbol2Buy, Symbol symbol3Sell, Ticker symbol1BuyTicker,
            Ticker symbol2BuyTicker, Ticker symbol3SellTicker) {
        this.symbol1Buy = symbol1Buy;
        this.symbol2Buy = symbol2Buy;
        this.symbol3Sell = symbol3Sell;
        this.symbol1BuyTicker = symbol1BuyTicker;
        this.symbol2BuyTicker = symbol2BuyTicker;
        this.symbol3SellTicker = symbol3SellTicker;
    }

    public Symbol getSymbol1Buy() {
        return symbol1Buy;
    }

    public void setSymbol1Buy(Symbol symbol1Buy) {
        this.symbol1Buy = symbol1Buy;
    }

    public Symbol getSymbol2Buy() {
        return symbol2Buy;
    }

    public void setSymbol2Buy(Symbol symbol2Buy) {
        this.symbol2Buy = symbol2Buy;
    }

    public Symbol getSymbol3Sell() {
        return symbol3Sell;
    }

    public void setSymbol3Sell(Symbol symbol3Sell) {
        this.symbol3Sell = symbol3Sell;
    }

    public Ticker getSymbol1BuyTicker() {
        return symbol1BuyTicker;
    }

    public void setSymbol1BuyTicker(Ticker symbol1BuyTicker) {
        this.symbol1BuyTicker = symbol1BuyTicker;
    }

    public Ticker getSymbol2BuyTicker() {
        return symbol2BuyTicker;
    }

    public void setSymbol2BuyTicker(Ticker symbol2BuyTicker) {
        this.symbol2BuyTicker = symbol2BuyTicker;
    }

    public Ticker getSymbol3SellTicker() {
        return symbol3SellTicker;
    }

    public void setSymbol3SellTicker(Ticker symbol3SellTicker) {
        this.symbol3SellTicker = symbol3SellTicker;
    }

    @Override
    public String toString() {
        return "TriangleArbitrage [symbol1Buy=" + symbol1Buy + ", symbol2Buy=" + symbol2Buy + ", symbol3Sell="
                + symbol3Sell + ", symbol1BuyTicker=" + symbol1BuyTicker + ", symbol2BuyTicker=" + symbol2BuyTicker
                + ", symbol3SellTicker=" + symbol3SellTicker + "]";
    }

}
