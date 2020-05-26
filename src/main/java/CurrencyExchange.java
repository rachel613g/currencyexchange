import com.google.gson.annotations.SerializedName;

public class CurrencyExchange
{
    String base;
    @SerializedName("conversion_rates")
    ConversionRates conversionRates;


    public static class ConversionRates
    {
        double USD;
        double ILS;

        public double getILS()
        {
            return ILS;
        }
    }


}
