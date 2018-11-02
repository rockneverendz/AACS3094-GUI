import javax.swing.*;
import java.awt.*;

public class Practical3Q5 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q5();
    }

    private Practical3Q5() {

        JLabel jlbTitle = new JLabel("Transfer Funds");
        JPanel jpnTeller = new JpnTeller();
        JPanel jpnTable = new JpnTable();
        JPanel jpnButton = new JPanel();
        JButton jbtOK = new JButton("OK");
        JButton jbtCancel = new JButton("Cancel");

        getContentPane().setLayout(new BoxLayout(super.getContentPane(), BoxLayout.Y_AXIS));

        jlbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlbTitle.setFont(new Font("SANS", Font.BOLD, 20));

        jpnButton.add(jbtOK);
        jpnButton.add(jbtCancel);
        jpnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(jlbTitle);
        add(jpnTeller);
        add(jpnTable);
        add(jpnButton);



        pack();
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class JpnTeller extends JPanel {
    private static JTextField fromTeller = new JTextField(10);
    private static JTextField toTeller = new JTextField();

    JpnTeller() {
        super.setLayout(new GridLayout(2, 2));
        add(new JLabel("From Teller", SwingConstants.RIGHT));
        add(fromTeller);
        add(new JLabel("To Teller", SwingConstants.RIGHT));
        add(toTeller);
    }
}

class JpnTable extends JPanel {
    private static JTextField quan100 = new JTextField(10);
    private static JTextField quan50 = new JTextField();
    private static JTextField quan10 = new JTextField();
    private static JTextField quan5 = new JTextField();
    private static JTextField quan1 = new JTextField();
    private static JTextField value100 = new JTextField();
    private static JTextField value50 = new JTextField();
    private static JTextField value10 = new JTextField();
    private static JTextField value5 = new JTextField();
    private static JTextField value1 = new JTextField();
    private static JTextField total = new JTextField();

    JpnTable() {
        super.setLayout(new GridLayout(7, 3));
        add(new JLabel("Denomination", SwingConstants.CENTER));
        add(new JLabel("Quantity", SwingConstants.CENTER));
        add(new JLabel("Value", SwingConstants.CENTER));
        add(new JLabel("100", SwingConstants.RIGHT));
        add(quan100);
        add(value100);
        add(new JLabel("50", SwingConstants.RIGHT));
        add(quan50);
        add(value50);
        add(new JLabel("10", SwingConstants.RIGHT));
        add(quan10);
        add(value10);
        add(new JLabel("5", SwingConstants.RIGHT));
        add(quan5);
        add(value5);
        add(new JLabel("1", SwingConstants.RIGHT));
        add(quan1);
        add(value1);
        add(new JLabel("Total", SwingConstants.RIGHT));
        add(new JLabel(""));
        add(total);

        value100.setEditable(false);
        value50.setEditable(false);
        value10.setEditable(false);
        value5.setEditable(false);
        value1.setEditable(false);
        total.setEditable(false);
    }
}