package br.com.tradx.tradxweb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tradx.tradxweb.dto.TickerDTO;
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

	@Test
	void meracadoBitcoinTicker() {
		System.out.println(mercadoBitcoinService.getAllSymbols());
	}
	
	@Test
	void meracadoBitcoinOrderbook() {
		System.out.println(binanceService.getOrderbook("BTCUSDT"));
	}

}
