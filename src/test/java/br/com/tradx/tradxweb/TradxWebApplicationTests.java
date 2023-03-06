package br.com.tradx.tradxweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.DollarApiService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;

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
		SymbolDTO t = mercadoBitcoinService.getSymbol("BTC");
		System.out.println(binanceService.getSymbol("BTCUSDT"));
		//System.out.println("\n\n\n\n\n\n\n\\" + t + "\n\n\n\n\n\n\n\\");
	}
	
	@Test
	void meracadoBitcoinOrderbook() {
		System.out.println(dollarService.getPrice());
	}

}
