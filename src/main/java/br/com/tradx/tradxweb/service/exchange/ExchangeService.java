package br.com.tradx.tradxweb.service.exchange;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

@Service
public interface ExchangeService {

    public SymbolDTO getSymbolData(String symbolName);

    public List<SymbolDTO> getSymbolData(List<String> symbols);

    public OrderbookDTO getOrderbook(String symbolName);

}
