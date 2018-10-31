import javax.swing.*;

public class Practical1Q2 extends JFrame {
    private Practical1Q2(){
        super("One Frame");
        add(new JButton("Hello World!"));
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Practical1Q2 userInterface = new Practical1Q2();

    }
}
