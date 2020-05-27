package gutmann.currencyexchange;

import gutmann.currencyexchange.CurrencyExchange;
import gutmann.currencyexchange.CurrencyExchangeCalculator;
import gutmann.currencyexchange.CurrencyExchangeController;
import gutmann.currencyexchange.CurrencyExchangeService;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.*;

public class CurrencyExchangeControllerTest
{

    @Test
    public void requestData()
    {
        //given
        Call<CurrencyExchange> call = mock(Call.class);
        String baseCurrency = "USD";
        CurrencyExchangeService service = mock(CurrencyExchangeService.class);

        doReturn(call).when(service).getCurrencyExchangeRate(baseCurrency);

        CurrencyExchangeCalculator calculator = mock(CurrencyExchangeCalculator.class);
        CurrencyExchangeController controller = new CurrencyExchangeController(baseCurrency, service, calculator);
        //when
        controller.requestData();
        //then
        //verify this method was called with these arguments
        verify(service).getCurrencyExchangeRate(baseCurrency);
        verify(call).enqueue(controller);
    }

    @Test
    public void onResponse()
    {
        //given
        String baseCurrency = "USD";
        CurrencyExchangeService service = mock(CurrencyExchangeService.class);
        CurrencyExchangeCalculator calculator = mock(CurrencyExchangeCalculator.class);
        CurrencyExchangeController controller = new CurrencyExchangeController(baseCurrency, service, calculator);

        Call<CurrencyExchange> call = mock(Call.class);
        Response<CurrencyExchange> response = mock(Response.class);

        CurrencyExchange currencyExchange = mock(CurrencyExchange.class);
        CurrencyExchange.ConversionRates conversionRates = mock(CurrencyExchange.ConversionRates.class);

        //mock response populating currencyExchange
        doReturn(currencyExchange).when(response).body();
        currencyExchange.conversionRates = conversionRates;
        when(conversionRates.getILS()).thenReturn(3.5263);

        //when
        controller.onResponse(call, response);

        //then
        verify(calculator).setRate(3.5263);
    }
}