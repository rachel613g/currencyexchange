import javafx.util.Callback;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CurrencyExchangeControllerTest
{

    //we will turn onResponse into an accessible method
    @Test
    public void requestData()
    {
        //given
        Call<CurrencyExchange> call = mock(Call.class);
        String baseCurrency = "USD";
        CurrencyExchangeService service = mock(CurrencyExchangeService.class);
        CurrencyExchangeCalculator calculator = mock(CurrencyExchangeCalculator.class);
        CurrencyExchangeController controller = new CurrencyExchangeController(baseCurrency, service, calculator);
        //when
        controller.requestData();
        //then
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

        //when
        controller.onResponse(call,response);

        //then
        assertEquals(3.5164,calculator.getRate(),0.01);
    }
}

