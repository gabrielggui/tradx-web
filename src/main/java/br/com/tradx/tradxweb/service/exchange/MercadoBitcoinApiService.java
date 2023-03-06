package br.com.tradx.tradxweb.service.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

import org.springframework.stereotype.Service;

@Service
public class MercadoBitcoinApiService extends ExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://www.mercadobitcoin.net/api/";

	@Override
	public SymbolDTO getSymbol(String symbolName) {

		String urlTicker = urlApi + symbolName + "/ticker";
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		JsonElement jsonElement = new Gson().fromJson(jsonStringResposta, JsonObject.class).get("ticker");

		SymbolDTO symbolDTO = new Gson().fromJson(jsonElement, SymbolDTO.class);
		symbolDTO.setName(symbolName);

		return symbolDTO;
	}

	@Override
	public OrderbookDTO getOrderbook(SymbolDTO symbolDTO) {

		String urlOrderbook = urlApi + symbolDTO.getName() + "/orderbook";

		OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);
		orderbookDTO.setSymbol(symbolDTO);

		return orderbookDTO;
	}

}
