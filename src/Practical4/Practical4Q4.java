import javax.swing.*;
import java.awt.*;

public class Practical4Q4 extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(Practical4Q4::new);
    }

    private Practical4Q4() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel JpnPassword = new JpnPassword();
        JButton jbtSubmit = new JButton("Submit");
        JTextPane jtpLog = new JTextPane();

        add(JpnPassword);
        add(jbtSubmit);
        add(jtpLog);
        jtpLog.setPreferredSize( new Dimension( 200, 200 ) );

        //jbtSubmit.addActionListener();

        pack();
        super.setTitle("Array");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnPassword extends JPanel {
    private static JPasswordField jtfPassword = new JPasswordField(15);

    JpnPassword() {
        super.setLayout(new GridLayout(1, 2));
        add(new JLabel("Enter your password"));
        add(jtfPassword);
    }


}