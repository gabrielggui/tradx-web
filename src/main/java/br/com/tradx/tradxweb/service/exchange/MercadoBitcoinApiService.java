package br.com.tradx.tradxweb.service.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

import org.springframework.stereotype.Service;

@Service
public class MercadoBitcoinApiService implements ExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";

	@Override
	public SymbolDTO getSymbolData(String symbolName) {
		String urlTicker = urlApi + "/tickers?symbols=" + symbolName;
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		JsonElement jsonElement = new Gson().fromJson(jsonStringResposta, JsonArray.class).get(0);

		SymbolDTO symbolDTO = new Gson().fromJson(jsonElement, SymbolDTO.class);

		return symbolDTO;
	}

	@Override
	public List<SymbolDTO> getSymbolData(List<String> symbols) {
		String symbolsParam = symbols.toString()
				.replace("[", "")
				.replace("]", "")
				.replace(" ", "");

		String urlTicker = urlApi + "/tickers?symbols=" + symbolsParam;
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		
		Type typeOfObjectsList = new TypeToken<List<SymbolDTO>>(){}.getType();
		List<SymbolDTO> symbolsList = new Gson().fromJson(jsonStringResposta, typeOfObjectsList);

		return symbolsList;
	}

	@Override
	public OrderbookDTO getOrderbook(String symbolName) {
		String urlOrderbook = urlApi + symbolName + "/orderbook";
		OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);

		if (orderbookDTO != null) {
			orderbookDTO.setPair(symbolName);
		}

		return orderbookDTO;
	}

}
