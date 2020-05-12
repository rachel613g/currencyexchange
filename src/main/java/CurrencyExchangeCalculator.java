import java.text.DecimalFormat;

public class CurrencyExchangeCalculator
{
    double rate;

    public double calculate(double baseCurrency)
    {
        double foreignEquivalent = baseCurrency * rate;
        DecimalFormat df = new DecimalFormat("#0.00");
        return Double.parseDouble(df.format(foreignEquivalent));
    }

    public void setCalculatorInput(double rate)
    {
        this.rate = rate;
    }
}
