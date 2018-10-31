import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practical2Q3 extends JFrame {
    private JButton jbtRed = new JButton("Red");
    private JButton jbtGreen = new JButton("Green");
    private JButton jbtBlue = new JButton("Blue");
    private JLabel labelColor = new JLabel("COLOR");

    public static void main(String[] args) {
        Practical2Q3 userInterface = new Practical2Q3();
    }

    private Practical2Q3() {
        setLayout(new GridLayout(2, 1));

        JPanel jpnLabel = new JPanel();
        JPanel jpnButton = new JPanel();

        jpnLabel.add(labelColor, BorderLayout.CENTER);
        //labelColor.setHorizontalAlignment(JLabel.CENTER);
        labelColor.setFont(new Font("SERIF", Font.BOLD, 20));

        jpnButton.add(jbtRed, BorderLayout.CENTER);
        jpnButton.add(jbtGreen, BorderLayout.CENTER);
        jpnButton.add(jbtBlue, BorderLayout.CENTER);

//        jbtRed.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                labelColor.setForeground(Color.RED);
//            }
//        });
//        jbtGreen.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                labelColor.setForeground(Color.GREEN);
//            }
//        });
//        jbtBlue.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                labelColor.setForeground(Color.BLUE);
//            }
//        });

        jbtRed.addActionListener(e -> labelColor.setForeground(Color.RED));
        jbtGreen.addActionListener(e -> labelColor.setForeground(Color.GREEN));
        jbtBlue.addActionListener(e -> labelColor.setForeground(Color.BLUE));

        add(jpnLabel);
        add(jpnButton);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}