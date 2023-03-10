package br.com.tradx.tradxweb;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.tradx.tradxweb.dto.SymbolDTO;
import br.com.tradx.tradxweb.dto.TickerDTO;
import br.com.tradx.tradxweb.service.exchange.BinanceApiService;
import br.com.tradx.tradxweb.service.exchange.MercadoBitcoinApiService;
import br.com.tradx.tradxweb.service.forex.DollarApiService;

@SpringBootTest
class TradxWebApplicationTests {

	@Autowired
	private MercadoBitcoinApiService mercadoBitcoinService;

	@Autowired
	private BinanceApiService binanceService;

	@Autowired
	private DollarApiService dollarService;

	@Autowired
	private RestTemplate restTemplate;

	private final String urlApi = "https://api.mercadobitcoin.net/api/v4/";
	@Test
	void meracadoBitcoinTicker() {
		List<String> symbolsList = new ArrayList<>();
		symbolsList.add("BTC-BRL");
		symbolsList.add("ETH-BRL");
		System.out.println(mercadoBitcoinService.getAllSymbols());
	}
	
	public void getSymbols() {

		String urlSymbols = urlApi + "symbols";
		String jsonStringResposta = restTemplate.getForObject(urlSymbols, String.class);
		JsonObject jsonObject = new Gson().fromJson(jsonStringResposta, JsonObject.class);

		
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

			String withdrawalFeeString = jsonObject.get("withdrawal-fee").getAsJsonArray().get(i).getAsString();
			String withdrawalMinimumString = jsonObject.get("withdrawal-fee").getAsJsonArray().get(i).getAsString();
			String depositMinimumString = jsonObject.get("withdrawal-fee").getAsJsonArray().get(i).getAsString();

			symbolDTO.setWithdrawalFee(withdrawalFeeString.equals("") ? 0.0 : Double.parseDouble(withdrawalFeeString));
			symbolDTO.setWithdrawalMinimum(
					withdrawalMinimumString.equals("") ? 0.0 : Double.parseDouble(withdrawalMinimumString));
			symbolDTO.setDepositMinimum(
					depositMinimumString.equals("") ? 0.0 : Double.parseDouble(depositMinimumString));
			symbolsList.add(symbolDTO);
		}
		System.out.println(symbolsList);
	}

	@Test
	void meracadoBitcoinOrderbook() {
		System.out.println(binanceService.getOrderbook("BTCUSDT"));
	}

}
