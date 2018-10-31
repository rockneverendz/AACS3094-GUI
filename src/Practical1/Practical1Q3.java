import javax.swing.*;
import java.awt.*;

public class Practical1Q3 extends JFrame {
    public static void main(String[] args) {
        StudentInfoFrameFlow userInterface = new StudentInfoFrameFlow();
    }
}

class StudentInfoFrameFlow extends JFrame {
    StudentInfoFrameFlow(){
        super("Student Information");
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        add(new JLabel("Registration Number"));
        add(new JTextField(10));
        add(new JLabel("Name"));
        add(new JTextField(10));
        add(new JLabel("Programme Code"));
        add(new JTextField(10));
        add(new JButton("Submit"));

        //setSize(500,100);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}