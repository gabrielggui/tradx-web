package br.com.tradx.tradxweb.model;

import org.springframework.stereotype.Component;

@Component
public class Ticker {

    private String pair;
    private Double high;
    private Double low;
    private Double vol;
    private Double last;
    private Double buy;
    private Double sell;
    private Long date;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SymbolDTO [pair=" + pair + ", high=" + high + ", low=" + low + ", vol=" + vol + ", last=" + last
                + ", buy=" + buy + ", sell=" + sell + ", date=" + date + "]";
    }

}
