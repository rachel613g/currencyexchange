import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrExchangeService
{
   @GET("/v5/9d5705c195ce3af5a08d04cf/latest/")
   Call<CurrencyExchange> getCurrencyExchangeRate(@Query("base_currency") String baseCurrency);
}
