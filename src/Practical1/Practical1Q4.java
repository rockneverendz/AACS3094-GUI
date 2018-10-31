import javax.swing.*;
import java.awt.*;

public class Practical1Q4 extends JFrame {
    public static void main(String[] args) {
        UserInterfaceP1Q6 userInterface = new UserInterfaceP1Q6();
    }
}

class StudentInfoFrameGird extends JFrame {
    StudentInfoFrameGird(){
        super("Student Information");
        setLayout(new GridLayout(4,2,10,10));

        add(new JLabel("Registration Number"));
        add(new JTextField(10));
        add(new JLabel("Name"));
        add(new JTextField(10));
        add(new JLabel("Programme Code"));
        add(new JTextField(10));
        add(new JButton("Submit"));

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}