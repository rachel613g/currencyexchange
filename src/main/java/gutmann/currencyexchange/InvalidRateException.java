package gutmann.currencyexchange;

public class InvalidRateException extends Exception
{
    public InvalidRateException(){
        super("Currency exchange rate is invalid.");
    }
}
