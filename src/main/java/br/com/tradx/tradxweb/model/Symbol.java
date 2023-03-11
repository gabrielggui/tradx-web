package br.com.tradx.tradxweb.model;

import java.util.UUID;

import br.com.tradx.tradxweb.dto.SymbolDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Symbol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String baseCurrency;

    @Column(nullable = false)
    private Boolean listed;

    @Column(nullable = false)
    private Boolean traded;

    @ManyToOne(cascade = CascadeType.ALL)
    private Exchange exchange;

    public Symbol() {
    }

    public Symbol(UUID id, String name, String description, String currency, String baseCurrency, Boolean listed,
            Boolean traded, Exchange exchange) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.baseCurrency = baseCurrency;
        this.listed = listed;
        this.traded = traded;
        this.exchange = exchange;
    }

    public Symbol(SymbolDTO symbolDTO) {
        this.name = symbolDTO.getName();
        this.description = symbolDTO.getDescription();
        this.currency = symbolDTO.getCurrency();
        this.baseCurrency = symbolDTO.getBaseCurrency();
        this.listed = symbolDTO.isListed();
        this.traded = symbolDTO.isTraded();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Boolean getListed() {
        return listed;
    }

    public void setListed(Boolean listed) {
        this.listed = listed;
    }

    public Boolean getTraded() {
        return traded;
    }

    public void setTraded(Boolean traded) {
        this.traded = traded;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Symbol [id=" + id + ", name=" + name + ", description=" + description + ", currency=" + currency
                + ", baseCurrency=" + baseCurrency + ", listed=" + listed + ", traded=" + traded + ", exchange="
                + exchange + "]";
    }
}
