package br.com.tradx.tradxweb.dto;

public class SymbolDTO {

    private String name;
    private String description;
    private String currency;
    private String baseCurrency;
    private boolean listed;
    private boolean traded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public boolean isListed() {
        return listed;
    }

    public void setListed(boolean listed) {
        this.listed = listed;
    }

    public boolean isTraded() {
        return traded;
    }

    public void setTraded(boolean traded) {
        this.traded = traded;
    }

    @Override
    public String toString() {
        return "SymbolDTO [name=" + name + ", description=" + description + ", currency=" + currency + ", baseCurrency="
                + baseCurrency + ", listed=" + listed + ", traded=" + traded + "]";
    }

}
