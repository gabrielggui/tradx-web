package br.com.tradx.tradxweb.service.exchange;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.model.Orderbook;
import br.com.tradx.tradxweb.model.Symbol;

@Component
public class BinanceExchangeService extends ExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";

    @Override
    public Symbol getSymbol(String symbolName) {
		String urlTicker = urlApi + "ticker/24hr?symbol=" + symbolName;
		String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
		JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        jsonObject = fixJsonKeyValues(jsonObject);

		Symbol symbol = new Gson().fromJson(jsonObject, Symbol.class);
		symbol.setName(symbolName);

		return symbol;
    }

    @Override
    public Orderbook getOrderbook(Symbol symbol) {
        String urlOrderbook = urlApi + "depth?symbol=" + symbol.getName();

        Orderbook orderbook = restTemplate.getForObject(urlOrderbook, Orderbook.class);
        orderbook.setSymbol(symbol);

        return orderbook;
    }

    private JsonObject fixJsonKeyValues(JsonObject jsonObject){
        
        String nameTmp = jsonObject.remove("symbol").getAsString();
        jsonObject.addProperty("name", nameTmp);

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
