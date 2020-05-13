import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyExchangeCalculatorTest
{

    @Test
    public void calculate()
    {
        //given
        CurrencyExchangeCalculator calculator = new CurrencyExchangeCalculator();
        calculator.setRate(3.5164);
        //when
        double calculateReturn = calculator.calculate(45.0);
        //then
        assertEquals(158.24, calculateReturn,0.01);
    }
}