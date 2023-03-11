package br.com.tradx.tradxweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.dto.TickerDTO;
import br.com.tradx.tradxweb.repository.SymbolRepository;
import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;
import br.com.tradx.tradxweb.service.forex.DollarApiService;

@SpringBootTest
class TradxWebApplicationTests {

	@Autowired
	private MercadoBitcoinApiService mercadoBitcoinService;

	@Autowired
	private BinanceApiService binanceService;

	@Autowired
	private DollarApiService dollarService;
	@Autowired
	private SymbolRepository symbolRepository;
	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";

	@Test
	void meracadoBitcoinTicker() {
		List<String> symbolsList = new ArrayList<>();
		System.out.println(symbolRepository.listCommonCurrencyPairsAmongExchanges());
	}

	@Test
	void meracadoBitcoinOrderbook() {
		System.out.println(binanceService.getOrderbook("BTCUSDT"));
	}

}
