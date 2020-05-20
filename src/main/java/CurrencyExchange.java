import java.util.HashMap;
import java.util.List;

public class CurrencyExchange
{
    String base;
    conversion_rates conversion_rates;


    public class conversion_rates
    {
        double USD;
        double CAD;
        double EUR;
        double ILS;
    }

    public double getILS()
    {
        return conversion_rates.ILS;
    }

}
