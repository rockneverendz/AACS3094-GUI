import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Practical3Q4 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q4();
    }

    private Practical3Q4() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel jpnCalc = new JpnCalc();
        JPanel jpnBttn = new JPanel();

        add(jpnCalc);
        add(jpnBttn);

        JButton jbtAdd = new JButton("Add");
        JButton jbtSub = new JButton("Subtract");
        JButton jbtMul = new JButton("Multiply");
        JButton jbtDiv = new JButton("Divide");

        jpnBttn.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jpnBttn.add(jbtAdd);
        jpnBttn.add(jbtSub);
        jpnBttn.add(jbtMul);
        jpnBttn.add(jbtDiv);

        jbtAdd.setMnemonic(KeyEvent.VK_A);
        jbtSub.setMnemonic(KeyEvent.VK_S);
        jbtMul.setMnemonic(KeyEvent.VK_M);
        jbtDiv.setMnemonic(KeyEvent.VK_D);

        jbtAdd.addActionListener(e -> {
            try {
                JpnCalc.add();
            } catch (NumberFormatException nFE) {
                numbrFrmtMsgger(nFE);
            }
        });
        jbtSub.addActionListener(e -> {
            try {
                JpnCalc.sub();
            } catch (NumberFormatException nFE) {
                numbrFrmtMsgger(nFE);
            }
        });
        jbtMul.addActionListener(e -> {
            try {
                JpnCalc.mul();
            } catch (NumberFormatException nFE) {
                numbrFrmtMsgger(nFE);
            }
        });
        jbtDiv.addActionListener(e -> {
            try {
                JpnCalc.div();
            } catch (NumberFormatException nFE) {
                numbrFrmtMsgger(nFE);
            }
        });

        pack();
        super.setTitle("Simple Calculator");
        //super.setSize(400,200);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void numbrFrmtMsgger(NumberFormatException e) {
        JOptionPane.showMessageDialog(
                this,
                "Invalid Number\n"+ e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        JpnCalc.clear();
        //e.printStackTrace();
    }
}


class JpnCalc extends JPanel {
    private static JTextField first = new JTextField(10);
    private static JTextField second = new JTextField();
    private static JTextField result = new JTextField();

    JpnCalc() {
        super.setLayout(new GridLayout(3, 2));
        add(new JLabel("First Number"));
        add(first);
        add(new JLabel("Second Number"));
        add(second);
        add(new JLabel("Result"));
        add(result);
        result.setEditable(false);
    }

    private static double getFirst() {
        return Double.parseDouble(first.getText());
    }

    private static double getSecond() {
        return Double.parseDouble(second.getText());
    }

    static void add() {
        result.setText(String.format("%.2f", getFirst() + getSecond()));
    }

    static void sub() {
        result.setText(String.format("%.2f", getFirst() - getSecond()));
    }

    static void mul() {
        result.setText(String.format("%.2f", getFirst() * getSecond()));
    }

    static void div() {
        result.setText(String.format("%.2f", getFirst() / getSecond()));
    }

    static void clear(){
        first.setText("");
        second.setText("");
        first.requestFocus();
    }
}