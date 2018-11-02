import javax.swing.*;
import java.awt.*;

public class BoxLayoutDemo extends JFrame{

    public static void main(String[] args) {
        new BoxLayoutDemo();
    }

    private BoxLayoutDemo() {
        setTitle("BoxLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(super.getContentPane());

        //Display the window.
        pack();
        setVisible(true);


        super.setLocationRelativeTo(null);
    }

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(new JButton("Button 1"));
        pane.add(new JButton("Button 2"));
        pane.add(new JButton("Button 3"));
        pane.add(new JButton("A long name button 4"));
        pane.add(new JButton("5"));
    }

    private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        //button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }
}