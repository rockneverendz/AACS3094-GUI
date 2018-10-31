import javax.swing.*;
import java.awt.*;

public class Practical1Q7 extends JFrame {
    public static void main(String[] args) {
        UserInterfaceP1Q7 userInterface = new UserInterfaceP1Q7();
    }
}

class UserInterfaceP1Q7 extends JFrame {
    UserInterfaceP1Q7(){
        super("Practical 1 Question 5");

        add(new buttonPanelP1Q7(),BorderLayout.SOUTH);
        add(new buttonPanelP1Q7(),BorderLayout.CENTER);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class buttonPanelP1Q7 extends JPanel {
    buttonPanelP1Q7(){
        setLayout(new GridLayout(1,2));
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
    }
}