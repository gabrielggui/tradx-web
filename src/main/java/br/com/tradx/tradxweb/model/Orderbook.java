package br.com.tradx.tradxweb.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Orderbook {

    private Symbol symbol;
    private List<List<Double>> asks;
    private List<List<Double>> bids;

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public List<List<Double>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Double>> asks) {
        this.asks = asks;
    }

    public List<List<Double>> getBids() {
        return bids;
    }

    public void setBids(List<List<Double>> bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return "Orderbook [symbol=" + symbol + ", asks=" + asks + ", bids=" + bids + "]";
    }
}
