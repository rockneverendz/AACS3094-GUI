/*
class Student extends Exception {
private static final String name = "Brendan Chew Jian Wen";
private static final String id = "17WMD05129";
private static final String programme = "DIA2";
private static final String group = "F1";
}
*/

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Practical3Q2 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q2();
    }

    Practical3Q2() {

        JPanel jpnInput = new JpnInput();
        JPanel jpnComplete = new JPanel(new FlowLayout());

        JButton jbtButton = new JButton("Complete Payment");
        jpnComplete.add(jbtButton);
        jpnComplete.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jbtButton.addActionListener(e -> {
            try {
                JpnInput.calc();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid number inserted",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        add(jpnInput, BorderLayout.CENTER);
        add(jpnComplete, BorderLayout.SOUTH);

        super.setTitle("Loan Calculator");
        super.setSize(400, 300);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnInput extends JPanel {
    private static JTextField annualInterestRate = new JTextField();
    private static JTextField numberOfYears = new JTextField();
    private static JTextField loanAmount = new JTextField();
    private static JTextField monthlyPayment = new JTextField();
    private static JTextField totalPayment = new JTextField();

    JpnInput() {
        setBorder(new TitledBorder("Enter loan amount, interest rate, and years"));
        super.setLayout(new GridLayout(5, 2));

        add(new JLabel("Annual Interest Rate"));
        add(annualInterestRate);
        add(new JLabel("Number of Years"));
        add(numberOfYears);
        add(new JLabel("Loan Amount"));
        add(loanAmount);
        add(new JLabel("Monthly Payment"));
        add(monthlyPayment);
        monthlyPayment.setEditable(false);
        add(new JLabel("Total Payment"));
        add(totalPayment);
        totalPayment.setEditable(false);
    }

    static void calc() {
        double x2 = Double.parseDouble(annualInterestRate.getText());
        int y2 = Integer.parseInt(numberOfYears.getText());
        double z2 = Double.parseDouble(loanAmount.getText());

        Loan loan = new Loan(x2, y2, z2);

        monthlyPayment.setText(String.format("%.2f", loan.getMonthlyPayment()));
        totalPayment.setText(String.format("%.2f", loan.getTotalPayment()));
    }
}