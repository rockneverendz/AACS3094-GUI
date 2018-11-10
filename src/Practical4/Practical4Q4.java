import com.sun.jdi.InvalidLineNumberException;

import javax.swing.*;
import java.awt.*;

public class Practical4Q4 extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(Practical4Q4::new);
    }

    private Practical4Q4() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel JpnPassword = new JPanel();
        JPasswordField jpfPassword = new JPasswordField();
        JButton jbtSubmit = new JButton("Submit");
        JTextArea jtaLog = new JTextArea();

        JpnPassword.setLayout(new GridLayout(1, 2));
        JpnPassword.add(new JLabel("Enter your password"));
        JpnPassword.add(jpfPassword);

        add(JpnPassword);
        add(jbtSubmit);
        add(jtaLog);
        jtaLog.setPreferredSize(new Dimension(200, 200));
        //jtaLog.setFont("SANS",Color.RED);
        jbtSubmit.addActionListener(e -> {
            try {
                Password password = new Password(jpfPassword.getPassword());
                jtaLog.setText("Valid!");
            } catch (InvalidPasswordException ex) {
                jtaLog.setText(ex.getMessage());

            }
        });

        //jbtSubmit.addActionListener();

        pack();
        super.setTitle("Array");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class Password {
    private String password = "";

    Password() {
    }

    Password(char[] password) throws InvalidPasswordException {
        StringBuilder errorMessage = new StringBuilder();
        boolean containLetter = false;
        boolean containDigit = false;

        if (password.length == 0)
            errorMessage.append("Cannot Null/Empty");
        if (password.length < 6)
            errorMessage.append("Cannot less then 7 character\n");

        for (int i = 0; i < password.length; i++)
            if (Character.isLetter(password[i])) {
                containLetter = true;
                i = password.length;
            }

        for (int i = 0; i < password.length; i++)
            if (Character.isDigit(password[i])) {
                containDigit = true;
                i = password.length;
            }

        if (!containLetter)
            errorMessage.append("Your password should have at least 1 letter\n");
        if (!containDigit)
            errorMessage.append("Your password should have at least 1 digit\n");

        if (!errorMessage.toString().isEmpty())
            throw new InvalidPasswordException(errorMessage.toString());
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {}
    InvalidPasswordException(String message) {
        super(message);
    }
}