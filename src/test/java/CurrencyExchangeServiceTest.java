
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CurrencyExchangeServiceTest
{

    @Test
    public void getCurrencyExchangeRate() throws IOException
    {
        //https://prime.exchangerate-api.com/v5/9d5705c195ce3af5a08d04cf/latest/USD
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prime.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);
        //when
        Response<CurrencyExchange> response = service.getCurrencyExchangeRate("USD").execute();
        CurrencyExchange currencyExchange = response.body();

        //then
        assertTrue(response.message(), response.isSuccessful());
        assertNotNull(currencyExchange.conversionRates.USD);
        assertNotNull(currencyExchange.conversionRates.ILS);
    }
}