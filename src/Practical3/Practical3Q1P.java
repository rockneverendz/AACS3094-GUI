import javax.swing.*;
import java.awt.*;

public class Practical3Q1P extends JFrame {
    public static void main(String[] args) {
        new Practical3Q1P();
    }

    private Practical3Q1P() {
        ImageIcon imgGrapes = new ImageIcon(getClass().getResource("images/grapes.gif"));
        JButton jbtGrape = new JButton("Grapes", imgGrapes);

        pack();
        JPanel jpnInput = new JPanel();

        super.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(new JpnButton(),c);

        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(new JpnAlign(),c);

        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        add(new JpnTextPos(),c);

        super.setTitle("Button Icons");
        super.setSize(500, 750);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnButton extends JPanel {
    JpnButton() {
        super.setSize(500,500);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/grapes.gif"));
        JButton jButton = new JButton("Grapes",imageIcon);
        jButton.setVerticalAlignment(SwingConstants.CENTER);


        add(jButton);
    }
}

class JpnTextPos extends JPanel {
    JpnTextPos() {
        super.setLayout(new GridLayout(3, 3));
        super.setSize(250,250);

        JButton navUP = new JButton("↑");
        JButton navLEFT = new JButton("←");
        JButton navCENTER = new JButton("╳");
        JButton navRIGHT = new JButton("→");
        JButton navDOWN = new JButton("↓");

        add(new JLabel());
        add(navUP);
        add(new JLabel());
        add(navLEFT);
        add(navCENTER);
        add(navRIGHT);
        add(new JLabel());
        add(navDOWN);
        add(new JLabel());
    }
}

class JpnAlign extends JPanel {
    JpnAlign() {
        super.setLayout(new GridLayout(3, 3));
        super.setSize(250,250);

        JButton navUP = new JButton("↑");
        JButton navLEFT = new JButton("←");
        JButton navCENTER = new JButton("╳");
        JButton navRIGHT = new JButton("→");
        JButton navDOWN = new JButton("↓");

        add(new JLabel());
        add(navUP);
        add(new JLabel());
        add(navLEFT);
        add(navCENTER);
        add(navRIGHT);
        add(new JLabel());
        add(navDOWN);
        add(new JLabel());
    }
}