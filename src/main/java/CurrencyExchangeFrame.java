import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyExchangeFrame extends JFrame
{
    JLabel inputBaseAmount;
    JTextField inputBaseAmountTextField;
    JButton enterButton;
    JLabel baseLabel;
    JTextArea baseTextArea;
    JLabel foreignEquivalentLabel;
    JTextArea foreignEquivalentTextArea;

    String baseCurrency = "USD";


    public CurrencyExchangeFrame()
    {
        setSize(new Dimension(500,500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convert US Dollars to Shekel");
        setLayout(new GridLayout());

        //initialize variable
        inputBaseAmount = new JLabel("Input Amount:");
        inputBaseAmountTextField = new JTextField();
        enterButton = new JButton("Enter");
        baseLabel = new JLabel("Base Currency");
        baseTextArea = new JTextArea();
        foreignEquivalentLabel = new JLabel("Shekel Equivalent");
        foreignEquivalentTextArea = new JTextArea();



        add(inputBaseAmount);
        add(inputBaseAmountTextField);
        add(enterButton);
        add(baseLabel);
        add(baseTextArea);
        add(foreignEquivalentLabel);
        add(foreignEquivalentTextArea);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prime.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);


        enterButton.addActionListener(actionEvent ->
        {
            CurrencyExchangeCalculator calculator = new CurrencyExchangeCalculator();
            CurrencyExchangeController controller = new CurrencyExchangeController(baseCurrency, service, calculator);
            controller.requestData();
            double calculate = calculator.calculate(Double.parseDouble(inputBaseAmountTextField.getText()));
            foreignEquivalentTextArea.append(Double.toString(calculate));
        });

    }

    public static void main(String[] args)
    {
        new CurrencyExchangeFrame().setVisible(true);
    }
}
