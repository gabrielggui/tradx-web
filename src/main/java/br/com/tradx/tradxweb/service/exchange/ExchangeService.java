package br.com.tradx.tradxweb.service.exchange;

import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

@Service
public interface ExchangeService {
    
    public SymbolDTO getSymbol(String symbolName);

    public OrderbookDTO getOrderbook(String symbolName);

}
