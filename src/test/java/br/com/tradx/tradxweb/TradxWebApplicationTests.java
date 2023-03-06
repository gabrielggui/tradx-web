package br.com.tradx.tradxweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tradx.tradxweb.model.Symbol;
import br.com.tradx.tradxweb.service.exchange.BinanceExchangeService;
import br.com.tradx.tradxweb.service.exchange.DollarService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinExchangeService;

@SpringBootTest
class TradxWebApplicationTests {


	@Autowired
	private MercadoBitcoinExchangeService mercadoBitcoinService;

	@Autowired
	private BinanceExchangeService binanceService;

	@Autowired
	private DollarService dollarService;

	@Test
	void meracadoBitcoinTicker() {
		Symbol t = mercadoBitcoinService.getSymbol("BTC");
		System.out.println(binanceService.getSymbol("BTCUSDT"));
		//System.out.println("\n\n\n\n\n\n\n\\" + t + "\n\n\n\n\n\n\n\\");
	}
	
	@Test
	void meracadoBitcoinOrderbook() {
		System.out.println(dollarService.getPrice());
	}

}
