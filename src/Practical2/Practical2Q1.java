import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practical2Q1 extends JFrame {
    private JButton jbtRed = new JButton("Red");
    private JButton jbtGreen = new JButton("Green");
    private JButton jbtBlue = new JButton("Blue");
    private JLabel labelColor = new JLabel("COLOR");

    public static void main(String[] args) {
        Practical2Q1 userInterface = new Practical2Q1();
    }

    private Practical2Q1() {
        setLayout(new GridLayout(2, 1));

        JPanel jpnLabel = new JPanel();
        JPanel jpnButton = new JPanel();

        jpnLabel.add(labelColor, BorderLayout.CENTER);
        //labelColor.setHorizontalAlignment(JLabel.CENTER);
        labelColor.setFont(new Font("SERIF", Font.BOLD, 20));

        jpnButton.add(jbtRed, BorderLayout.CENTER);
        jpnButton.add(jbtGreen, BorderLayout.CENTER);
        jpnButton.add(jbtBlue, BorderLayout.CENTER);

        jbtRed.addActionListener(new ButtonListenerClass());
        jbtGreen.addActionListener(new ButtonListenerClass());
        jbtBlue.addActionListener(new ButtonListenerClass());

        add(jpnLabel);
        add(jpnButton);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonListenerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbtRed) labelColor.setForeground(Color.red);
            else if (e.getSource() == jbtGreen) labelColor.setForeground(Color.green);
            else if (e.getSource() == jbtBlue) labelColor.setForeground(Color.blue);
            else JOptionPane.showMessageDialog(null, "How the fuck", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}