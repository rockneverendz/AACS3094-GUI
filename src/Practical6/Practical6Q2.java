/*
class Student extends Exception {
private static final String name = "Brendan Chew Jian Wen";
private static final String id = "17WMD05129";
private static final String programme = "DIA2";
private static final String group = "F1";
}
*/

import javax.swing.*;
import java.awt.*;

public class Practical6Q2 extends JFrame {
    private static JTextField jtfName = new JTextField();
    private static JTextField jtfID = new JTextField();

    private static String[] programme = {
            "DIA2",
            "DIB2",
            "DIT2",
            "DST2"
    };

    private static String[] software = {
            "Windows 8",
            "Windows 10",
            "Visual Studio 2015",
            "SQL Server"
    };

    private static JComboBox<String> jcbProgramme = new JComboBox<>(programme); //<> to let compiler verify type safety
    private static JCheckBox[] jcbSoftware = new JCheckBox[software.length]; //Initialize an array with the number of software

    private static int jrbIndex = -1;

    private static JButton jbtConfirm = new JButton("Confirm");
    private static JButton jbtClear = new JButton("Clear");
    private static JButton jbtExit = new JButton("Exit");

    public static void main(String[] args) {
        EventQueue.invokeLater(Practical6Q2::new);
    }

    private Practical6Q2() {

        JPanel jpnNorth = new JPanel();
        jpnNorth.setLayout(new GridLayout(2, 2));
        jpnNorth.add(new JLabel("Name"));
        jpnNorth.add(jtfName);
        jpnNorth.add(new JLabel("ID"));
        jpnNorth.add(jtfID);

        JPanel jpnWest = new JPanel();
        jpnWest.setLayout(new GridLayout(5, 1));
        jpnWest.add(new JLabel("Programme"));
        jpnWest.add(jcbProgramme);
        jcbProgramme.setSelectedIndex(-1);

        JPanel jpnEast = new JPanel();
        jpnEast.setLayout(new GridLayout(5, 1));
        jpnEast.add(new JLabel("Software"));
        for (int i = 0; i < software.length; i++) {
            jcbSoftware[i] = new JCheckBox(software[i]); //Add name to object
            jpnEast.add(jcbSoftware[i]); //Add object to panel
        }

        JPanel jpnSouth = new JPanel();
        jpnSouth.setLayout(new FlowLayout());
        jpnSouth.add(jbtConfirm);
        jpnSouth.add(jbtClear);
        jpnSouth.add(jbtExit);

        add(jpnNorth, BorderLayout.NORTH);
        add(jpnWest, BorderLayout.WEST);
        add(jpnEast, BorderLayout.EAST);
        add(jpnSouth, BorderLayout.SOUTH);

        jbtConfirm.addActionListener(e -> {
            try {
                confirmListener();
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Programme selection cannot be null",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        jbtClear.addActionListener(e -> {
            jtfID.setText("");
            jtfName.setText("");
            jcbProgramme.setSelectedIndex(-1);
            for (JCheckBox jcb : jcbSoftware) {
                jcb.setSelected(false);
            }
        });
        jbtExit.addActionListener(e -> System.exit(0));

        setTitle("CheckOutSystem");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static void confirmListener() throws Exception {
        boolean containSoftware = false; //Used to check if there's at least one software selected

        if (jtfName.getText().isEmpty())
            throw new Exception("Name field cannot be empty");
        if (jtfID.getText().isEmpty())
            throw new Exception("ID field cannot be empty");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Name : ").append(jtfName.getText()).append('\n')
                .append("ID : ").append(jtfID.getText()).append('\n')
                .append("Programme : ").append(jcbProgramme.getSelectedItem()).append('\n') //If -1, throw IndexOutOfBoundsException.
                .append("Software ------- ").append('\n');
        for (int i = 0; i < software.length; i++) {
            if (jcbSoftware[i].isSelected()) {
                stringBuilder.append(software[i]).append('\n');
                containSoftware = true;
            }
        }
        if (!containSoftware) //If none checkboxes are selected, throw.
            throw new Exception("Software selection cannot be null");

        stringBuilder.append("\n\nIs the above information correct?");

        int n = JOptionPane.showConfirmDialog(
                null,
                stringBuilder.toString(),
                "Check Information",
                JOptionPane.YES_NO_OPTION
        );

        if (n == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(
                    null,
                    "YEET",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE
            );
    }
}