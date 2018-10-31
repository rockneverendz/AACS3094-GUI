import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Practical3Q2 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q2();
    }

    private Practical3Q2() {

        JPanel jpnInput = new JpnInput();
        JPanel jpnComplete = new JPanel(new FlowLayout());

        JButton jbtButton = new JButton("Complete Payment");
        jpnComplete.add(jbtButton);
        jpnComplete.setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(jpnInput, BorderLayout.CENTER);
        add(jpnComplete, BorderLayout.SOUTH);

        super.setTitle("Loan Calculator");
        super.setSize(400, 300);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnInput extends JPanel{
    JTextField annualInterestRate = new JTextField();
    JTextField numberOfYears = new JTextField();
    JTextField loanAmount = new JTextField();
    JTextField monthlyPayment = new JTextField();
    JTextField totalPayment = new JTextField();

    JpnInput(){
        setBorder(new TitledBorder("Enter loan amount, interest rate, and years"));
        super.setLayout(new GridLayout(5,2));

        add(new JLabel("Annual Interest Rate"));
        add(annualInterestRate);
        add(new JLabel("Number of Years"));
        add(numberOfYears);
        add(new JLabel("Loan Amount"));
        add(loanAmount);
        add(new JLabel("Monthly Payment"));
        add(monthlyPayment);
        add(new JLabel("Total Payment"));
        add(totalPayment);
    }

    void JpnInputCalc(){
        double x = Double.parseDouble(annualInterestRate.toString());
        int y = Integer.parseInt(numberOfYears.toString());
        double z = Double.parseDouble(loanAmount.toString());

        Loan loan = new Loan(x, y, z);


    }
}