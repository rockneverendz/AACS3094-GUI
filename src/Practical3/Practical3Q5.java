import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private static JTextField value100 = new JTextField("0.00");
    private static JTextField value50 = new JTextField("0.00");
    private static JTextField value10 = new JTextField("0.00");
    private static JTextField value5 = new JTextField("0.00");
    private static JTextField value1 = new JTextField("0.00");
    private static JTextField total = new JTextField("0.00");

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

        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                if (isDigit(text)) fb.replace(offset, length, text, attrs);
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

            /*
             * Q  -  Why not use this to calculate the value
             *       from the quantity inserted by the user?
             * A  -  1. This is a filter. Nothing more, nothing less.
             *       2. We can't getSource() from this.
             *          Meaning we don't know where the input is coming from.
             *          And checking every field every time
             *          the user updates is inefficient.
             *          Thus I let documentListener do that task.
             *
             * Q  -  Where's the remove method?
             * A  -  There's no need to check what user removed.
             *
             * Q  -  Where's the insertString method?
             * A  -  That method only gets invoked when
             *       textField.getDocument().insertString() is used.
             *       When the user edit the textField,
             *       it only invokes replace method.
             */
        };

        DocumentListener documentListener = new DocumentListener() {
            private int int100 = 0;
            private int int50 = 0;
            private int int10 = 0;
            private int int5 = 0;
            private int int1 = 0;

            @Override
            public void insertUpdate(DocumentEvent e) {
                sort(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                sort(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void sort(DocumentEvent e) {
                if (e.getDocument() == quan100.getDocument())
                    int100 = update(100, quan100, value100);
                else if (e.getDocument() == quan50.getDocument())
                    int50 = update(50, quan50, value50);
                else if (e.getDocument() == quan10.getDocument())
                    int10 = update(10, quan10, value10);
                else if (e.getDocument() == quan5.getDocument())
                    int5 = update(5, quan5, value5);
                else
                    int1 = update(1, quan1, value1);
                total.setText(int100 + int50 + int10 + int5 + int1 + ".00");
            }

            private int update(int denominator, JTextField quantity, JTextField value) {
                int i;
                if (!quantity.getText().isEmpty())
                    i = Integer.parseInt(quantity.getText()) * denominator;
                else
                    i = 0;

                value.setText(i + ".00");
                return i;
            }
        };

        ((AbstractDocument) (quan100.getDocument())).setDocumentFilter(documentFilter);
        ((AbstractDocument) (quan50.getDocument())).setDocumentFilter(documentFilter);
        ((AbstractDocument) (quan10.getDocument())).setDocumentFilter(documentFilter);
        ((AbstractDocument) (quan5.getDocument())).setDocumentFilter(documentFilter);
        ((AbstractDocument) (quan1.getDocument())).setDocumentFilter(documentFilter);
        quan100.getDocument().addDocumentListener(documentListener);
        quan50.getDocument().addDocumentListener(documentListener);
        quan10.getDocument().addDocumentListener(documentListener);
        quan5.getDocument().addDocumentListener(documentListener);
        quan1.getDocument().addDocumentListener(documentListener);

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