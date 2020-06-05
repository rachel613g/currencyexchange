package gutmann.currencyexchange;

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

    CurrencyExchangeService service;
    CurrencyExchangeController controller;
    CurrencyExchangeCalculator calculator;


    public CurrencyExchangeFrame() throws InvalidRateException
    {
        setSize(new Dimension(800, 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convert US Dollars to Shekel");
        setLayout(new GridLayout());

        //initialize variables
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
        //should I have initialized this in constructor before this method is called?
        service = new CurrencyExchangeServiceFactory().getInstance();
        calculator = new CurrencyExchangeCalculator();
        controller = new CurrencyExchangeController(baseCurrency, service, calculator);
        controller.requestData(Double.parseDouble(inputBaseAmountTextField.getText()));

        baseTextArea.append(inputBaseAmountTextField.getText() + "$");

        if(controller.errorMessage != null)
        {
            foreignEquivalentTextArea.append(controller.errorMessage);
        }
        foreignEquivalentTextArea.append(Double.toString(controller.returnValue) + "\u20AA");
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
