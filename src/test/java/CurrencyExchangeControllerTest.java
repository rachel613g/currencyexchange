import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.*;
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

        CurrencyExchange currencyExchange = new CurrencyExchange();

        when(currencyExchange.getILS()).thenReturn(3.564);
        doReturn(currencyExchange).when(response).body();
        //when
        controller.requestData();

                //then
        verify(currencyExchange);
        verify(calculator).setRate(currencyExchange.getILS());
    }
}