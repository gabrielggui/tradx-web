package br.com.arbify.arbifyweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.arbify.arbifyweb.dto.Orderbook;
import br.com.arbify.arbifyweb.model.Symbol;
import br.com.arbify.arbifyweb.model.Ticker;

@Service
public interface ExchangeService {

    public List<Symbol> getSymbols(List<String> symbols);

    public List<Symbol> getAllSymbols();

    public Symbol getSymbol(String symbolName);

    public Ticker getTicker(String ticker);

    public List<Ticker> getTickers(List<String> tickers);

    public List<Ticker> getAllTickers();

    public Orderbook getOrderbook(String symbolName);

    public String exchangeName();
}
