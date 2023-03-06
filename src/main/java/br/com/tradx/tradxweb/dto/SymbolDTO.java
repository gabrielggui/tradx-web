package br.com.tradx.tradxweb.dto;

public class SymbolDTO {

    private String name;
    private Double high;
    private Double low;
    private Double vol;
    private Double last;
    private Double buy;
    private Double sell;
    private Long date;

    public SymbolDTO() {
    }

    public SymbolDTO(String name) {
        this.name = name;
    }

    public SymbolDTO(String name, Double high, Double low, Double vol, Double last, Double buy, Double sell, Long date) {
        this.name = name;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.last = last;
        this.buy = buy;
        this.sell = sell;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Symbol [name=" + name + ", high=" + high + ", low=" + low + ", vol=" + vol + ", last=" + last + ", buy="
                + buy + ", sell=" + sell + ", date=" + date + "]";
    }

}
