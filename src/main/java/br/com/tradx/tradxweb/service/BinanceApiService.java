package br.com.tradx.tradxweb.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.Orderbook;
import br.com.tradx.tradxweb.model.Symbol;
import br.com.tradx.tradxweb.model.Ticker;

@Component
public class BinanceApiService implements ExchangeService {

    private static final String EXCHANGE_NAME = "Binance";

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://api.binance.com/api/v3/";

    @Override
    public List<Symbol> getSymbols(List<String> symbols) {
        String symbolsParam = "?symbols=" + symbols.toString().replace(" ", "");
        String urlSymbols = urlApi + "exchangeInfo" + (symbols.size() != 0 ? symbolsParam : "");
        String jsonStringResposta = restTemplate.getForObject(urlSymbols, String.class);
        JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        if (jsonObject == null)
            return null;

        JsonArray jsonArray = jsonObject.get("symbols").getAsJsonArray();
        List<Symbol> symbolsList = new ArrayList<>();

        int jsonArraySize = jsonArray.size();
        for (int i = 0; i < jsonArraySize; i++) {
            Symbol symbol = new Symbol();
            String symbolStatus = jsonArray.get(i).getAsJsonObject().get("status").getAsString();

            if (!symbolStatus.equals("TRADING"))
                continue;

            symbol.setName(jsonArray.get(i).getAsJsonObject().get("symbol").getAsString());
            symbol.setDescription(jsonArray.get(i).getAsJsonObject().get("symbol").getAsString());
            symbol.setCurrency(jsonArray.get(i).getAsJsonObject().get("quoteAsset").getAsString());
            symbol.setBaseCurrency(jsonArray.get(i).getAsJsonObject().get("baseAsset").getAsString());
            symbol.setListed(true);
            symbol.setTraded(true);

            symbolsList.add(symbol);
        }
        return symbolsList;
    }

    @Override
    public List<Symbol> getAllSymbols() {
        return getSymbols(new ArrayList<>());
    }

    @Override
    public Symbol getSymbol(String symbolName) {
        return getSymbols(List.of(symbolName)).get(0);
    }

    @Override
    public List<Ticker> getTickers(List<String> tickers) {
        String tickersParam = tickers
                .parallelStream()
                .map(ticker -> String.format("\"%s\"", ticker))
                .toList().toString().replace(" ", "");
        String urlTicker = urlApi + "ticker/24hr" + (tickers.size() > 0 ? "?symbols=" + tickersParam : "");

        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonArray jsonArray = new Gson().fromJson(jsonStringResposta, JsonArray.class);

        List<Ticker> tickerList = jsonArray.asList().parallelStream()
                .map(jsonElement -> fixJsonKeyValues(jsonElement.getAsJsonObject()))
                .map(jsonObject -> new Gson().fromJson(jsonObject, Ticker.class))
                .toList();

        return tickerList;
    }

    @Override
    public Ticker getTicker(String tickerName) {
        String urlTicker = urlApi + "ticker/24hr?symbol=" + tickerName;
        String jsonStringResposta = restTemplate.getForObject(urlTicker, String.class);
        JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

        jsonObject = fixJsonKeyValues(jsonObject);

        Ticker ticker = new Gson().fromJson(jsonObject, Ticker.class);
        ticker.setPair(tickerName);

        return ticker;
    }

    @Override
    public List<Ticker> getAllTickers() {
        return getTickers(new ArrayList<String>());
    }

    @Override
    public Orderbook getOrderbook(String tickerName) {
        String urlOrderbook = urlApi + "depth?symbol=" + tickerName;

        Orderbook orderbook = restTemplate.getForObject(urlOrderbook, Orderbook.class);

        if (orderbook != null) {
            orderbook.setPair(tickerName);
        }

        return orderbook;
    }

    @Override
    public String exchangeName() {
        return EXCHANGE_NAME;
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
