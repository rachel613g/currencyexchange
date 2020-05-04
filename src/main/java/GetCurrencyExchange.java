import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCurrencyExchange
{
    public GetCurrencyExchange(){

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://prime.exchangerate-api.com/v5")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        CurrExchangeService service = retrofit.create(CurrExchangeService.class);

        service.getCurrencyExchangeRate();

        /*
        todo
        add callback class
        and an .enqueue type of command
         */
    }

}
