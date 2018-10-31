import javax.swing.*;
import java.awt.*;

public class Practical1Q6 extends JFrame {
    public static void main(String[] args) {
        UserInterfaceP1Q6 userInterface = new UserInterfaceP1Q6();
    }
}

class UserInterfaceP1Q6 extends JFrame {
    UserInterfaceP1Q6(){
        super("Practical 1 Question 5");

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(1,2));
        buttonPanel1.add(new JButton("This is a button"));
        buttonPanel1.add(new JButton("Another button"));

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new GridLayout(1,2));
        buttonPanel2.add(new JButton("Too many button"));
        buttonPanel2.add(new JButton("Stop"));

        add(buttonPanel1,BorderLayout.SOUTH);
        add(buttonPanel2,BorderLayout.CENTER);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}