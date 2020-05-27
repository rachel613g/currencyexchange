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
    //may change app to allow user input of basecurrency in the future
    String baseCurrency = "USD";

    Retrofit retrofit;
    CurrencyExchangeService service;
    CurrencyExchangeController controller;
    CurrencyExchangeCalculator calculator;


    public CurrencyExchangeFrame() throws InvalidRateException
    {
        setSize(new Dimension(500, 500));
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
/*
todo
make a FactoryClass to create service
 */
        retrofit = new Retrofit.Builder()
                .baseUrl("https://prime.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CurrencyExchangeService.class);

        enterButton.addActionListener(actionEvent -> enterUserInputRequestTheData());
        clearButton.addActionListener(actionEvent -> clearFrame());
    }

    private void enterUserInputRequestTheData()
    {
        calculator = new CurrencyExchangeCalculator();
        controller = new CurrencyExchangeController(baseCurrency, service, calculator);
        controller.requestData();
        double calculate = 0;
        try
        {
            calculate = calculator.calculate(Double.parseDouble(inputBaseAmountTextField.getText()));
            Thread thread = new Thread();
            thread.sleep(10);
        } catch (InvalidRateException | InterruptedException e)
        {
            foreignEquivalentTextArea.append(e.getMessage());
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
