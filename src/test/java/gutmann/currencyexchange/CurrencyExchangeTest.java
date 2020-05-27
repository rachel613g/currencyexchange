package gutmann.currencyexchange;

import gutmann.currencyexchange.CurrencyExchange;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyExchangeTest
{
    @Test
    public void getILS()
    {
        CurrencyExchange.ConversionRates conversionRates = new CurrencyExchange.ConversionRates();

        double getILS = conversionRates.getILS();

        assertEquals(3.546, getILS,0);
    }
}