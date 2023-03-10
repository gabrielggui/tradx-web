package br.com.tradx.tradxweb.service.exchange;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.dto.TickerDTO;

@Service
public interface ExchangeService {

    public List<SymbolDTO> getSymbols(List<String> symbols);

    public List<SymbolDTO> getAllSymbols();

    public SymbolDTO getSymbol(String symbolName);

    public TickerDTO getTicker(String ticker);

    public List<TickerDTO> getTickers(List<String> tickers);

    public OrderbookDTO getOrderbook(String symbolName);

}
