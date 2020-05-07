
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
        Response<CurrencyExchange> response = service.getCurrencyExchangeRate("USD").execute();

        //then
        assertTrue(response.message(), response.isSuccessful());
        //assertNotNull(currencyExchange.conversion_rates);
        //assertFalse(currencyExchange.conversion_rates);
    }
}