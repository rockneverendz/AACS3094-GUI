import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class Practical3Q5 extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(Practical3Q5::new);
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

        DocumentFilter df = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcAndSetTotal();
                }
            }

            @Override
            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcAndSetTotal();
            }

            @Override
            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcAndSetTotal();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);//get a single character of the string
                    //System.out.println(c);
                    if (!Character.isDigit(c)) {//if its an alphabetic character or white space
                        return false;
                    }
                }
                return true;
            }

            void calcAndSetTotal() {
                int sum = 0;



                if (!quan100.getText().isEmpty()) {
                    sum += Integer.parseInt(quan100.getText());
                }
                if (!quan50.getText().isEmpty()) {
                    sum += Integer.parseInt(quan50.getText());
                }
                if (!quan10.getText().isEmpty()) {
                    sum += Integer.parseInt(quan10.getText());
                }
                if (!quan5.getText().isEmpty()) {
                    sum += Integer.parseInt(quan5.getText());
                }
                if (!quan1.getText().isEmpty()) {
                    sum += Integer.parseInt(quan1.getText());
                }

                total.setText(String.valueOf(sum));
            }
        };

        ((AbstractDocument) (quan100.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (quan50.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (quan10.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (quan5.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (quan1.getDocument())).setDocumentFilter(df);

        value100.setEditable(false);
        value50.setEditable(false);
        value10.setEditable(false);
        value5.setEditable(false);
        value1.setEditable(false);
        total.setEditable(false);

        value100.setFocusable(false);
        value50.setFocusable(false);
        value10.setFocusable(false);
        value5.setFocusable(false);
        value1.setFocusable(false);
        total.setFocusable(false);

        quan100.setHorizontalAlignment(JTextField.RIGHT);
        quan50.setHorizontalAlignment(JTextField.RIGHT);
        quan10.setHorizontalAlignment(JTextField.RIGHT);
        quan5.setHorizontalAlignment(JTextField.RIGHT);
        quan1.setHorizontalAlignment(JTextField.RIGHT);
        value100.setHorizontalAlignment(JTextField.RIGHT);
        value50.setHorizontalAlignment(JTextField.RIGHT);
        value10.setHorizontalAlignment(JTextField.RIGHT);
        value5.setHorizontalAlignment(JTextField.RIGHT);
        value1.setHorizontalAlignment(JTextField.RIGHT);
        total.setHorizontalAlignment(JTextField.RIGHT);
    }
}