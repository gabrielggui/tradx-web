package br.com.tradx.tradxweb.service.exchange;

import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.constants.OrderbookType;
import br.com.tradx.tradxweb.model.Orderbook;
import br.com.tradx.tradxweb.model.Symbol;

@Service
public abstract class ExchangeService {
    
    public abstract Symbol getSymbol(String symbolName);

    public abstract Orderbook getOrderbook(Symbol symbol);

    public Orderbook getOrderBook(String symbolName) {
        return getOrderbook(new Symbol(symbolName));
    }

    public double getLastBidPrice(Orderbook orderbook) {
        return orderbook.getBids().get(0).get(OrderbookType.PRICE);
    }

    public double getLastAskPrice(Orderbook orderbook) {
        return orderbook.getAsks().get(0).get(OrderbookType.PRICE);
    }

    public double getLastBidVolume(Orderbook orderbook) {
        return orderbook.getBids().get(0).get(OrderbookType.VOLUME);
    }

    public double getLastAskVolume(Orderbook orderbook) {
        return orderbook.getAsks().get(0).get(OrderbookType.VOLUME);
    }

}
