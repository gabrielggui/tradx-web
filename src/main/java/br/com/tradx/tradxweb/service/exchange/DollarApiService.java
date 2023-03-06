package br.com.tradx.tradxweb.service.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class DollarApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String urlApi = "https://economia.awesomeapi.com.br/json/last/USD-BRL";

    public double getPrice() {
        String jsonStringResposta = restTemplate.getForObject(urlApi, String.class);
        JsonObject jsonObject = new Gson()
                .fromJson(jsonStringResposta, JsonObject.class)
                .getAsJsonObject("USDBRL");

        return jsonObject.get("ask").getAsDouble();
    }
}