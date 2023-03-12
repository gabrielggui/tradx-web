package br.com.tradx.tradxweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.model.Symbol;
import br.com.tradx.tradxweb.model.Ticker;
import br.com.tradx.tradxweb.service.BinanceApiService;

@SpringBootTest
class TradxWebApplicationTests {

	@Autowired
	private BinanceApiService binanceService;

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";


}
