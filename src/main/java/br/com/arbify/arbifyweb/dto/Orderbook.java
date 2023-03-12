package br.com.arbify.arbifyweb.dto;

import java.util.List;

public class Orderbook {

    private String pair;
    private List<List<Double>> asks;
    private List<List<Double>> bids;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
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
        return "OrderbookDTO [pair=" + pair + ", asks=" + asks + ", bids=" + bids + "]";
    }
}
