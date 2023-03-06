package br.com.tradx.tradxweb.dto;

import java.util.List;

public class OrderbookDTO {

    private String symbolName;
    private List<List<Double>> asks;
    private List<List<Double>> bids;

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
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
        return "OrderbookDTO [symbolName=" + symbolName + ", asks=" + asks + ", bids=" + bids + "]";
    }
}
