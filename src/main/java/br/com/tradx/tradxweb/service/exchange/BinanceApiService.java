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
import br.com.tradx.tradxweb.dto.TickerDTO;

@Component
public class BinanceApiService implements ExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";

    public SymbolDTO getSymbol(String symbolName){
        return null;
    }

    public List<SymbolDTO> getAllSymbols(){
        return null;
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
