import javax.swing.*;
import java.awt.*;

public class Practical3Q5 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q5();
    }

    private Practical3Q5() {

        JLabel jlbTitle = new JLabel("Transfer Funds");
        //TODO Bold, Center
        JPanel jpnTeller = new JpnTeller();
        JPanel jpnTable = new JpnTable();
        JPanel jpnButton = new JPanel();

        jlbTitle.setLayout(new BoxLayout(jlbTitle,BoxLayout.Y_AXIS));
        jpnTeller.setLayout(new BoxLayout(jpnTeller,BoxLayout.Y_AXIS));
        jpnTable.setLayout(new BoxLayout(jpnTable,BoxLayout.Y_AXIS));
        jpnButton.setLayout(new BoxLayout(jpnButton,BoxLayout.Y_AXIS));



        add(jlbTitle, BorderLayout.CENTER);
        add(jpnTeller);
        add(jpnTable);
        add(jpnButton);

        pack();
        super.setTitle("Login");
        //super.setSize(400,200);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnTeller extends JPanel {
    private static JTextField fromTeller = new JTextField(10);
    private static JTextField toTeller = new JTextField();

    JpnTeller() {
        super.setLayout(new GridLayout(2, 2));
        add(new JLabel("From Teller"));
        add(fromTeller);
        add(new JLabel("To Teller"));
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
        add(new JLabel("Denomination"));
        add(new JLabel("Quantity"));
        add(new JLabel("Value"));
        add(new JLabel("100"));
        add(quan100);
        add(value100);
        add(new JLabel("50"));
        add(quan50);
        add(value50);
        add(new JLabel("10"));
        add(quan10);
        add(value10);
        add(new JLabel("5"));
        add(quan5);
        add(value5);
        add(new JLabel("1"));
        add(quan1);
        add(value1);
        add(total);


    }
}