package br.com.tradx.tradxweb.service.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.model.Orderbook;
import br.com.tradx.tradxweb.model.Symbol;
import org.springframework.stereotype.Service;

@Service
public class MercadoBitcoinExchangeService extends ExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://www.mercadobitcoin.net/api/";

	@Override
	public Symbol getSymbol(String symbolName) {

		String urlTicker = urlApi + symbolName + "/ticker";
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		JsonElement jsonElement = new Gson().fromJson(jsonStringResposta, JsonObject.class).get("ticker");

		Symbol symbol = new Gson().fromJson(jsonElement, Symbol.class);
		symbol.setName(symbolName);

		return symbol;
	}

	@Override
	public Orderbook getOrderbook(Symbol symbol) {

		String urlOrderbook = urlApi + symbol.getName() + "/orderbook";

		Orderbook orderbook = restTemplate.getForObject(urlOrderbook, Orderbook.class);
		orderbook.setSymbol(symbol);

		return orderbook;
	}

}
