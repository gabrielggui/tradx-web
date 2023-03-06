package br.com.tradx.tradxweb.service.exchange;

import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.constants.OrderbookType;
import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

@Service
public abstract class ExchangeService {
    
    public abstract SymbolDTO getSymbol(String symbolName);

    public abstract OrderbookDTO getOrderbook(SymbolDTO symbolDTO);

    public OrderbookDTO getOrderBook(String symbolName) {
        return getOrderbook(new SymbolDTO(symbolName));
    }

    public double getLastBidPrice(OrderbookDTO orderbookDTO) {
        return orderbookDTO.getBids().get(0).get(OrderbookType.PRICE);
    }

    public double getLastAskPrice(OrderbookDTO orderbookDTO) {
        return orderbookDTO.getAsks().get(0).get(OrderbookType.PRICE);
    }

    public double getLastBidVolume(OrderbookDTO orderbookDTO) {
        return orderbookDTO.getBids().get(0).get(OrderbookType.VOLUME);
    }

    public double getLastAskVolume(OrderbookDTO orderbookDTO) {
        return orderbookDTO.getAsks().get(0).get(OrderbookType.VOLUME);
    }

}
