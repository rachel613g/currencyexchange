
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;


public class CurrExchangeServiceTest
{

    @Test
    void getCurrencyExchangeRate() throws IOException
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prime.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrExchangeService service = retrofit.create(CurrExchangeService.class);
        //when
        CurrencyExchange currencyExchange = service.getCurrencyExchangeRate("USD").execute().body();

        //then
        assertNotNull(currencyExchange.conversion_rates);
        //assertFalse(currencyExchange.conversion_rates);
    }
}