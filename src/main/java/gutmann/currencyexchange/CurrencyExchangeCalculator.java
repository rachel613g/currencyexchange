package gutmann.currencyexchange;

import java.text.DecimalFormat;

public class CurrencyExchangeCalculator
{
    double rate;

    public double calculate(double baseCurrency) throws InvalidRateException
    {
        if (rate <= 0)
        {
            throw new InvalidRateException();
        }
        double foreignEquivalent = baseCurrency * rate;
        DecimalFormat df = new DecimalFormat("#0.00");
        return Double.parseDouble(df.format(foreignEquivalent));
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public double getRate()
    {
        return rate;
    }
}
