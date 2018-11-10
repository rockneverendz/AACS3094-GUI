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
        jbtSubmit.addActionListener(e -> {
            try {
                Password password = new Password(jpfPassword.getPassword());
                jtaLog.setForeground(Color.blue);
                jtaLog.setText("Valid!");
            } catch (InvalidPasswordException ex) {
                jtaLog.setForeground(Color.red);
                jtaLog.setText(ex.getMessage());
            }
        });

        this.getRootPane().setDefaultButton(jbtSubmit);

        pack();
        super.setTitle("Array");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class Password {
    Password(char[] password) throws InvalidPasswordException {
        StringBuilder errorMessage = new StringBuilder();
        boolean containLetter = false;
        boolean containDigit = false;

        //Checking if the password is long enough
        if (password.length < 7)
            //If password is NULL, no more is required to check.
            if (password.length == 0)
                throw new InvalidPasswordException("Password cannot be null or empty");
            else
                errorMessage.append("Password must have at least 7 alpha-numeric characters\n");

        //Checking if there's at least one letter
        for (int i = 0; i < password.length; i++)
            if (Character.isLetter(password[i])) {
                containLetter = true;
                i = password.length;
            }

        //Checking if there's at least one digit
        for (int i = 0; i < password.length; i++)
            if (Character.isDigit(password[i])) {
                containDigit = true;
                i = password.length;
            }
            
        if (!containLetter)
            errorMessage.append("Password must have at least 1 letter\n");
        if (!containDigit)
            errorMessage.append("Password must have at least 1 digit\n");

        if (!errorMessage.toString().isEmpty())
            throw new InvalidPasswordException(errorMessage.toString());
    }
}

class InvalidPasswordException extends Exception {
    InvalidPasswordException(String message) {
        super(message);
    }
}