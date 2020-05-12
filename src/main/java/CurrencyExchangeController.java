import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyExchangeController
{
    CurrencyExchangeService service;
    CurrencyExchangeCalculator calculator;
    String baseCurrency;
    public CurrencyExchangeController(String baseCurrency, CurrencyExchangeService service, CurrencyExchangeCalculator calculator)
    {
        this.baseCurrency = baseCurrency;
        this.service = service;
        this.calculator = calculator;
    }

    public void requestData()
    {
        service.getCurrencyExchangeRate(baseCurrency).enqueue(new Callback<CurrencyExchange>()
        {
            @Override
            public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response)
            {
                CurrencyExchange currencyExchange = response.body();
                calculator.setCalculatorInput(currencyExchange.conversion_rates.ILS);
            }

            @Override
            public void onFailure(Call<CurrencyExchange> call, Throwable t)
            {

            }
        });
    }
}
