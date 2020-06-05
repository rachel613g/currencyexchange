package gutmann.currencyexchange;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyExchangeController implements Callback<CurrencyExchange>
{
    CurrencyExchangeService service;
    CurrencyExchangeCalculator calculator;
    String baseCurrency;
    double returnValue;
    double baseAmount;
    String errorMessage;


    /**
     * pass in dependencies because of Dependency Injection
     * makes testing easier
     * @param baseCurrency
     * @param service
     * @param calculator
     */
    public CurrencyExchangeController(String baseCurrency, CurrencyExchangeService service, CurrencyExchangeCalculator calculator)
    {
        this.baseCurrency = baseCurrency;
        this.service = service;
        this.calculator = calculator;
    }

    public void requestData(double baseAmount)
    {
        this.baseAmount = baseAmount;
        service.getCurrencyExchangeRate(baseCurrency).enqueue(this);
    }

    @Override
    public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response)
    {
        CurrencyExchange currencyExchange = response.body();
        //changed from just getting directly from conversionRates class to getter
        //because I used getILS() in a test
        calculator.setRate(currencyExchange.conversionRates.getILS());
        try
        {
            returnValue = calculator.calculate(baseAmount);
        } catch (InvalidRateException e)
        {
           errorMessage = e.getMessage();
        }
    }

    @Override
    public void onFailure(Call<CurrencyExchange> call, Throwable t)
    {
        t.printStackTrace();
    }


}
