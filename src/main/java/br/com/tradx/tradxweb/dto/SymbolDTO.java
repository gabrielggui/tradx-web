package br.com.tradx.tradxweb.dto;

public class SymbolDTO {
    private String name;
    private String description;
    private String currency;
    private String baseCurrency;
    private boolean listed;
    private boolean traded;
    private double withdrawalFee;
    private double withdrawalMinimum;
    private double depositMinimum;

    public SymbolDTO() {
    }

    public SymbolDTO(String name, String description, String currency, String baseCurrency, boolean listed,
            boolean traded, double withdrawalFee, double withdrawalMinimum, double depositMinimum) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.baseCurrency = baseCurrency;
        this.listed = listed;
        this.traded = traded;
        this.withdrawalFee = withdrawalFee;
        this.withdrawalMinimum = withdrawalMinimum;
        this.depositMinimum = depositMinimum;
    }

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

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(double withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public double getWithdrawalMinimum() {
        return withdrawalMinimum;
    }

    public void setWithdrawalMinimum(double withdrawalMinimum) {
        this.withdrawalMinimum = withdrawalMinimum;
    }

    public double getDepositMinimum() {
        return depositMinimum;
    }

    public void setDepositMinimum(double depositMinimum) {
        this.depositMinimum = depositMinimum;
    }

    @Override
    public String toString() {
        return "SymbolDTO [name=" + name + ", description=" + description + ", currency=" + currency + ", baseCurrency="
                + baseCurrency + ", listed=" + listed + ", traded=" + traded + ", withdrawalFee=" + withdrawalFee
                + ", withdrawalMinimum=" + withdrawalMinimum + ", depositMinimum=" + depositMinimum + "]";
    }
}
