import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyExchangeService
{
   @GET("/v5/9d5705c195ce3af5a08d04cf/latest/{base_currency}")
   Call<CurrencyExchange> getCurrencyExchangeRate(@Path("base_currency") String baseCurrency);
}
//for some reason when I used a @Query instead, gradle couldn't find the URL
//this is what I had written. In the error message I saw that it had base_currency? included.
//this is what I wrote before
//@Query("base_currency") String baseCurrency
//I have realized that my test passes but never ends