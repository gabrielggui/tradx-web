package br.com.tradx.tradxweb.service.exchange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.dto.TickerDTO;

@Component
public class BinanceApiService implements ExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";
    
	@Override
	public List<SymbolDTO> getSymbols(List<String> symbols) {
		String symbolsParam = "?symbols=" + symbols.toString()
				.replace("[", "")
				.replace("]", "")
				.replace(" ", "");
		String urlSymbols = urlApi + "symbols" + (symbols.size() != 0 ? symbolsParam : "");
		String jsonStringResposta = restTemplate.getForObject(urlSymbols, String.class);
		JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

		if (jsonObject == null)
			return null;

		List<SymbolDTO> symbolsList = new ArrayList<>();

		int jsonArraySize = jsonObject.get("symbol").getAsJsonArray().size();
		for (int i = 0; i < jsonArraySize; i++) {
			SymbolDTO symbolDTO = new SymbolDTO();
			symbolDTO.setName(jsonObject.get("symbol").getAsJsonArray().get(i).getAsString());
			symbolDTO.setDescription(jsonObject.get("description").getAsJsonArray().get(i).getAsString());
			symbolDTO.setCurrency(jsonObject.get("currency").getAsJsonArray().get(i).getAsString());
			symbolDTO.setBaseCurrency(jsonObject.get("base-currency").getAsJsonArray().get(i).getAsString());
			symbolDTO.setListed(jsonObject.get("exchange-listed").getAsJsonArray().get(i).getAsBoolean());
			symbolDTO.setTraded(jsonObject.get("exchange-traded").getAsJsonArray().get(i).getAsBoolean());

			symbolsList.add(symbolDTO);
		}
		return symbolsList;
	}

	@Override
	public List<SymbolDTO> getAllSymbols() {
		return getSymbols(new ArrayList<>());
	}

	@Override
	public SymbolDTO getSymbol(String symbolName) {
		return getSymbols(List.of(symbolName)).get(0);
	}

    @Override
    public TickerDTO getTicker(String tickerName) {
        String urlTicker = urlApi + "ticker/24hr?symbol=" + tickerName;
        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        jsonObject = fixJsonKeyValues(jsonObject);

        TickerDTO tickerDTO = new Gson().fromJson(jsonObject, TickerDTO.class);
        tickerDTO.setPair(tickerName);

        return tickerDTO;
    }

    @Override
    public List<TickerDTO> getTickers(List<String> tickers) {
        String tickersParam = tickers
                .parallelStream()
                .map(ticker -> String.format("\"%s\"", ticker))
                .toList().toString().replace(" ", "");
        String urlTicker = urlApi + "ticker/24hr?symbols=" + tickersParam;

        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonArray jsonArray = new Gson().fromJson(jsonStringResposta, JsonArray.class);

        List<TickerDTO> tickerList = jsonArray.asList().parallelStream()
                .map(jsonElement -> fixJsonKeyValues(jsonElement.getAsJsonObject()))
                .map(jsonObject -> new Gson().fromJson(jsonObject, TickerDTO.class))
                .toList();

        return tickerList;
    }

    @Override
    public OrderbookDTO getOrderbook(String tickerName) {
        String urlOrderbook = urlApi + "depth?symbol=" + tickerName;

        OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);

        if (orderbookDTO != null) {
            orderbookDTO.setPair(tickerName);
        }

        return orderbookDTO;
    }

    private JsonObject fixJsonKeyValues(JsonObject jsonObject) {

        String pairTmp = jsonObject.remove("symbol").getAsString();
        jsonObject.addProperty("pair", pairTmp);

        double highTmp = jsonObject.remove("highPrice").getAsDouble();
        jsonObject.addProperty("high", highTmp);

        double lowTmp = jsonObject.remove("lowPrice").getAsDouble();
        jsonObject.addProperty("low", lowTmp);

        double volTmp = jsonObject.remove("volume").getAsDouble();
        jsonObject.addProperty("vol", volTmp);

        double lastTmp = jsonObject.remove("lastPrice").getAsDouble();
        jsonObject.addProperty("last", lastTmp);

        double buyTmp = jsonObject.remove("bidPrice").getAsDouble();
        jsonObject.addProperty("buy", buyTmp);

        double sellTmp = jsonObject.remove("askPrice").getAsDouble();
        jsonObject.addProperty("sell", sellTmp);

        long dataEmEraUnix = LocalDate.now().toEpochDay() * 86400L;
        jsonObject.addProperty("date", dataEmEraUnix);

        return jsonObject;
    }
}
