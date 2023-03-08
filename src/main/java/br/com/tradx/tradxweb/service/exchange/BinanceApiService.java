package br.com.tradx.tradxweb.service.exchange;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

@Component
public class BinanceApiService implements ExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";

    @Override
    public SymbolDTO getSymbolData(String symbolName) {
        String urlTicker = urlApi + "ticker/24hr?symbol=" + symbolName;
        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        jsonObject = fixJsonKeyValues(jsonObject);

        SymbolDTO symbolDTO = new Gson().fromJson(jsonObject, SymbolDTO.class);
        symbolDTO.setPair(symbolName);

        return symbolDTO;
    }

    @Override
    public List<SymbolDTO> getSymbolData(List<String> symbols) {
        String symbolsParam = symbols
                .parallelStream()
                .map(symbol -> String.format("\"%s\"", symbol))
                .toList().toString().replace(" ", "");
        String urlTicker = urlApi + "ticker/24hr?symbols=" + symbolsParam;

        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonArray jsonArray = new Gson().fromJson(jsonStringResposta, JsonArray.class);

        List<SymbolDTO> symbolsList = jsonArray.asList().parallelStream()
                .map(jsonElement -> fixJsonKeyValues(jsonElement.getAsJsonObject()))
                .map(jsonObject -> new Gson().fromJson(jsonObject, SymbolDTO.class))
                .toList();

        return symbolsList;
    }

    @Override
    public OrderbookDTO getOrderbook(String symbolName) {
        String urlOrderbook = urlApi + "depth?symbol=" + symbolName;

        OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);

        if (orderbookDTO != null) {
            orderbookDTO.setPair(symbolName);
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
