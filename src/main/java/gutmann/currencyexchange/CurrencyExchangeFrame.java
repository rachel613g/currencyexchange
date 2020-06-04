package gutmann.currencyexchange;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrencyExchangeFrame extends JFrame
{
    JLabel inputBaseAmount;
    JTextField inputBaseAmountTextField;
    JButton enterButton;
    JButton clearButton;
    JLabel baseLabel;
    JTextArea baseTextArea;
    JLabel foreignEquivalentLabel;
    JTextArea foreignEquivalentTextArea;

    //for now the base currency is dollars
    //may change app to allow user input of basecurrency and foreign currency in the future
    String baseCurrency = "USD";

    Retrofit retrofit;
    CurrencyExchangeService service;
    CurrencyExchangeController controller;
    CurrencyExchangeCalculator calculator;


    public CurrencyExchangeFrame() throws InvalidRateException
    {
        setSize(new Dimension(800, 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convert US Dollars to Shekel");
        setLayout(new GridLayout());

        //initialize variable
        inputBaseAmount = new JLabel("Input Amount:");
        inputBaseAmountTextField = new JTextField();
        enterButton = new JButton("Enter");
        clearButton = new JButton("Clear");
        baseLabel = new JLabel("Base Currency");
        baseTextArea = new JTextArea();
        foreignEquivalentLabel = new JLabel("Shekel Equivalent");
        foreignEquivalentTextArea = new JTextArea();


        add(inputBaseAmount);
        add(inputBaseAmountTextField);
        add(enterButton);
        add(clearButton);
        add(baseLabel);
        add(baseTextArea);
        add(foreignEquivalentLabel);
        add(foreignEquivalentTextArea);


        enterButton.addActionListener(actionEvent -> enterUserInputRequestTheData());
        clearButton.addActionListener(actionEvent -> clearFrame());
    }

    private void enterUserInputRequestTheData()
    {
        //should I have initialized this in before this method is called?
        //service is initialized within the ServiceFactory class. In general how do I know if one class
        //initializes the other? Or was what I did a bad design?
        service = new CurrencyExchangeServiceFactory().getInstance();
        calculator = new CurrencyExchangeCalculator();
        controller = new CurrencyExchangeController(baseCurrency, service, calculator);
        controller.requestData();
        double calculate = 0;
        try
        {
            calculate = calculator.calculate(Double.parseDouble(inputBaseAmountTextField.getText()));

        } catch (InvalidRateException e)
        {
            foreignEquivalentTextArea.append("Error" + e.getMessage());
        }
        baseTextArea.append(inputBaseAmountTextField.getText() + "$");
        foreignEquivalentTextArea.append(Double.toString(calculate) + "\u20AA");
    }

    private void clearFrame()
    {
        inputBaseAmountTextField.setText(null);
        baseTextArea.setText(null);
        foreignEquivalentTextArea.setText(null);
    }

    public static void main(String[] args) throws InvalidRateException
    {
        new CurrencyExchangeFrame().setVisible(true);
    }
}
