package br.com.tradx.tradxweb.service.exchange;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.OrderbookDTO;
import br.com.tradx.tradxweb.dto.SymbolDTO;

@Component
public class BinanceApiService implements ExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";

    @Override
    public SymbolDTO getSymbol(String symbolName) {
        String urlTicker = urlApi + "ticker/24hr?symbol=" + symbolName;
        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        jsonObject = fixJsonKeyValues(jsonObject);

        SymbolDTO symbolDTO = new Gson().fromJson(jsonObject, SymbolDTO.class);
        symbolDTO.setName(symbolName);

        return symbolDTO;
    }

    @Override
    public OrderbookDTO getOrderbook(String symbolName) {
        String urlOrderbook = urlApi + "depth?symbol=" + symbolName;

        OrderbookDTO orderbookDTO = restTemplate.getForObject(urlOrderbook, OrderbookDTO.class);

        if (orderbookDTO != null) {
            orderbookDTO.setSymbolName(symbolName);
        }

        return orderbookDTO;
    }

    private JsonObject fixJsonKeyValues(JsonObject jsonObject) {

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
