package br.com.tradx.tradxweb.dto;

import java.util.List;

public class OrderbookDTO {

    private SymbolDTO symbol;
    private List<List<Double>> asks;
    private List<List<Double>> bids;

    public SymbolDTO getSymbol() {
        return symbol;
    }

    public void setSymbol(SymbolDTO symbol) {
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
