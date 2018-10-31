import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEventListener extends JFrame {
    private JButton jbtOK = new JButton("OK");
    private JButton jbtCancel = new JButton("Cancel");

    public ButtonEventListener() {
        setLayout(new FlowLayout());
        add(jbtOK);
        add(jbtCancel);
        jbtOK.addActionListener(new ButtonListenerClass());
        jbtCancel.addActionListener(new ButtonListenerClass());

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        ButtonEventListener userInterface = new ButtonEventListener();
    }

    private class ButtonListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbtOK) JOptionPane.showMessageDialog(null, "OK button clicked");
            else if (e.getSource() == jbtCancel) JOptionPane.showMessageDialog(null, "Cancel button clicked.");
        }
    }
}