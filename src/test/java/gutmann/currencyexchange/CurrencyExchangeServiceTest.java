package gutmann.currencyexchange;

import gutmann.currencyexchange.CurrencyExchange;
import gutmann.currencyexchange.CurrencyExchangeService;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.*;


public class CurrencyExchangeServiceTest
{

    @Test
    public void getCurrencyExchangeRate() throws IOException
    {

        CurrencyExchangeService service = new CurrencyExchangeServiceFactory().getInstance();
        //when
        Response<CurrencyExchange> response = service.getCurrencyExchangeRate("USD").execute();
        CurrencyExchange currencyExchange = response.body();

        //then
        assertTrue(response.message(), response.isSuccessful());
        assertNotNull(currencyExchange.conversionRates.USD);
        assertNotNull(currencyExchange.conversionRates.ILS);
    }
}