import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyExchangeController implements Callback<CurrencyExchange>
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
        //below we pass in the controller class because it is a implementation of Callback interface
        //instead of the anonymous Callback class we had before
        service.getCurrencyExchangeRate(baseCurrency).enqueue(this);
    }

    @Override
    public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response)
    {
        CurrencyExchange currencyExchange = response.body();
        calculator.setRate(currencyExchange.conversion_rates.ILS);
    }

    @Override
    public void onFailure(Call<CurrencyExchange> call, Throwable t)
    {

    }


}
