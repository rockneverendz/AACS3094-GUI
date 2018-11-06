import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.text.NumberFormat;

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

        /*
         * getContentPane() to select the JFrame's global panel. setLayout() to set the
         * panel's layout. And for some reason BoxLayout only applies for panels, so
         * setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); doesn't work.
         */
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        jlbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlbTitle.setFont(new Font("SANS", Font.BOLD, 20));

        jpnButton.add(jbtOK);
        jpnButton.add(jbtCancel);
        jpnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jbtOK.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                jpnTeller.toString() + jpnTable.toString()));

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

    @Override
    public String toString() {
        return "From teller = " + fromTeller.getText() + "\n" +
                "To teller = " + toTeller.getText() + "\n";
    }
}

class JpnTable extends JPanel {
    private static JFormattedTextField quan100 = new JFormattedTextField(NumberFormat.getNumberInstance());
    private static JFormattedTextField quan50 = new JFormattedTextField("0");
    private static JFormattedTextField quan10 = new JFormattedTextField("0");
    private static JFormattedTextField quan5 = new JFormattedTextField("0");
    private static JFormattedTextField quan1 = new JFormattedTextField("0");
    private static JFormattedTextField value100 = new JFormattedTextField("0.00");
    private static JFormattedTextField value50 = new JFormattedTextField("0.00");
    private static JFormattedTextField value10 = new JFormattedTextField("0.00");
    private static JFormattedTextField value5 = new JFormattedTextField("0.00");
    private static JFormattedTextField value1 = new JFormattedTextField("0.00");
    private static JFormattedTextField total = new JFormattedTextField("0.00");

    /*TODO find commitEdit callers
    https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html#constructors
    Some formatters might update the value constantly,
    rendering the loss of focus meaningless,
    as the value is always the same as what the text specifies.
    */
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

        DocumentFilter digitFilter = new DocumentFilter() {
            private int int100 = 0;
            private int int50 = 0;
            private int int10 = 0;
            private int int5 = 0;
            private int int1 = 0;

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (fb.getDocument().getLength() != length)
                    super.remove(fb, offset, length);
                else
                    super.replace(fb, offset, length, "0", null);
                sort(fb);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                if (isDigit(text)) {
                    super.replace(fb, offset, length, text, attrs);
                    sort(fb);
                }
            }

            /**
             * Used to check inserted string has any alphabetic character
             * or white space.
             * @param text String to check
             */
            private boolean isDigit(String text) {
                for (int n = 0; n < text.length(); n++) {
                    char c = text.charAt(n);
                    if (!Character.isDigit(c)) return false;
                }
                return true;
            }

            /**
             * Find out which document it was called from,
             * calls to update() then update the total value
             */
            private void sort(FilterBypass fb) {
                if (fb.getDocument() == quan100.getDocument())
                    int100 = update(100, quan100, value100);
                else if (fb.getDocument() == quan50.getDocument())
                    int50 = update(50, quan50, value50);
                else if (fb.getDocument() == quan10.getDocument())
                    int10 = update(10, quan10, value10);
                else if (fb.getDocument() == quan5.getDocument())
                    int5 = update(5, quan5, value5);
                else
                    int1 = update(1, quan1, value1);
                total.setText(int100 + int50 + int10 + int5 + int1 + ".00");
            }

            /**
             * Calls from sort() to update given fields
             * @param denominator Value to multiply
             * @param quantity textField to get quantity
             * @param value textField to set value
             */
            private int update(int denominator, JFormattedTextField quantity, JFormattedTextField value) {
                int i;
                if (!quantity.getText().isEmpty())
                    i = Integer.parseInt(quantity.getText()) * denominator;
                else
                    i = 0;

                value.setText(i + ".00");
                return i;
            }
        };

        ((AbstractDocument) (quan100.getDocument())).setDocumentFilter(digitFilter);
        ((AbstractDocument) (quan50.getDocument())).setDocumentFilter(digitFilter);
        ((AbstractDocument) (quan10.getDocument())).setDocumentFilter(digitFilter);
        ((AbstractDocument) (quan5.getDocument())).setDocumentFilter(digitFilter);
        ((AbstractDocument) (quan1.getDocument())).setDocumentFilter(digitFilter);

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String quan100s = quan100.getText();
        String quan50s = quan50.getText();
        String quan10s = quan10.getText();
        String quan5s = quan5.getText();
        String quan1s = quan1.getText();

        if (!(quan100s.isEmpty() || quan100s.equals("0")))
            stringBuilder.append("Denom = 100 Qty = ").append(quan100s).append("\n");
        if (!(quan50s.isEmpty() || quan50s.equals("0")))
            stringBuilder.append("Denom = 50  Qty = ").append(quan50s).append("\n");
        if (!(quan10s.isEmpty() || quan10s.equals("0")))
            stringBuilder.append("Denom = 10  Qty = ").append(quan10s).append("\n");
        if (!(quan5s.isEmpty() || quan5s.equals("0")))
            stringBuilder.append("Denom = 5   Qty = ").append(quan5s).append("\n");
        if (!(quan1s.isEmpty() || quan1s.equals("0")))
            stringBuilder.append("Denom = 1   Qty = ").append(quan1s).append("\n");

        return stringBuilder.toString();
    }
}