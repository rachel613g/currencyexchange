

public interface CurrExchangeService
{
   @GET("")
   Call<CurrencyExchange> getCurrencyExchangeRate();
}
