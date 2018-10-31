import javax.swing.*;
import java.awt.*;

public class Practical1Q5 extends JFrame {
    public static void main(String[] args) {
        UserInterfaceP1Q5 userInterface = new UserInterfaceP1Q5();
    }
}

class UserInterfaceP1Q5 extends JFrame {
    UserInterfaceP1Q5(){
        super("Practical 1 Question 5");
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JPanel buttonPanel1 = new JPanel();

        buttonPanel1.add(new JButton("This is a button"));
        buttonPanel1.add(new JButton("Another button"));

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(new JButton("Too many button"));
        buttonPanel2.add(new JButton("Stop"));

        add(buttonPanel1);
        add(buttonPanel2);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}