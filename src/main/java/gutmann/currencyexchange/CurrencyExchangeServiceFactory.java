package gutmann.currencyexchange;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyExchangeServiceFactory
{
    public CurrencyExchangeService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prime.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);
        return service;
    }
}
