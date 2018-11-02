import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Practical3Q3 extends JFrame {
    public static void main(String[] args) {
        new Practical3Q3();
    }

    private Practical3Q3() {
        setLayout(new GridLayout(2,1));

        JPanel jpnLgin = new JpnPass();
        JPanel jpnBttn = new JPanel();

        add(jpnLgin);
        add(jpnBttn);

        JButton jbtLgin = new JButton("Login");
        JButton jbtRset = new JButton("Reset");

        jpnBttn.add(jbtLgin);
        jpnBttn.add(jbtRset);
        jpnBttn.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jbtLgin.addActionListener(e -> JpnPass.verfiy());
        jbtRset.addActionListener(e -> JpnPass.reset());

        pack();
        super.setTitle("Login");
        //super.setSize(400,200);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}


class JpnPass extends JPanel{
    private static JTextField username = new JTextField(10);
    private static JPasswordField password = new JPasswordField();

    JpnPass(){
        super.setLayout(new GridLayout(2,2));

        add(new JLabel("Username"));
        add(username);
        add(new JLabel("Password"));
        add(password);
    }

    static void verfiy(){
        String trueUsername = "Verniy";
        char[] truePassword = {'1','2','3'};

        if (username.getText().equals(trueUsername) && Arrays.equals(password.getPassword(),truePassword)){
            JOptionPane.showMessageDialog(null,"FUCK YOU " + username.getText());
            new Practical3Q2();
        }
        else
            JOptionPane.showMessageDialog(null,"FUCK OFF");
    }

    static void reset() {
        username.setText(null);
        password.setText(null);
    }
}