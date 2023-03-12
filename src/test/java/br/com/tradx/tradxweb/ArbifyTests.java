package br.com.tradx.tradxweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.arbify.arbifyweb.model.Symbol;
import br.com.arbify.arbifyweb.model.Ticker;
import br.com.arbify.arbifyweb.service.BinanceApiService;

@SpringBootTest
class ArbifyTests {

	@Autowired
	private BinanceApiService binanceService;

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";


}
