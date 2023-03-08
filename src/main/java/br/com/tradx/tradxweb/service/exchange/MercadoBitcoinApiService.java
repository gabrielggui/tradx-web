package br.com.tradx.tradxweb.service.exchange;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.dto.TickerDTO;

import org.springframework.stereotype.Service;

@Service
public class MercadoBitcoinApiService implements ExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";

    public SymbolDTO getSymbol(String symbolName){
        return null;
    }

    public List<SymbolDTO> getAllSymbols(){
		String urlSymbols = urlApi + "symbols";
		JsonObject jsonObject = restTemplate.getForObject(urlSymbols,  JsonObject.class);

		List<SymbolDTO> symbols = new ArrayList<>();
		
		jsonObject.get("").getAsJsonArray().asList();


        return null;
    }

	@Override
	public TickerDTO getTicker(String tickerName) {
		String urlTicker = urlApi + "/tickers?symbols=" + tickerName;
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		JsonElement jsonElement = new Gson().fromJson(jsonStringResposta, JsonArray.class).get(0);

		TickerDTO tickerDTO = new Gson().fromJson(jsonElement, TickerDTO.class);

		return tickerDTO;
	}

	@Override
	public List<TickerDTO> getTickers(List<String> tickers) {
		String tickersParam = tickers.toString()
				.replace("[", "")
				.replace("]", "")
				.replace(" ", "");

		String urlTicker = urlApi + "/tickers?symbols=" + tickersParam;
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		
		Type typeOfObjectsList = new TypeToken<List<TickerDTO>>(){}.getType();
		List<TickerDTO> tickerList = new Gson().fromJson(jsonStringResposta, typeOfObjectsList);

		return tickerList;
	}

	@Override
	public OrderbookDTO getOrderbook(String tickerName) {
		String urlOrderbook = urlApi + tickerName + "/orderbook";
		OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);

		if (orderbookDTO != null) {
			orderbookDTO.setPair(tickerName);
		}

		return orderbookDTO;
	}

}
