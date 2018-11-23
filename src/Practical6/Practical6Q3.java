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

public class Practical6Q3 extends JFrame {
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

    private static ButtonGroup btg = new ButtonGroup();
    private static JRadioButton[] jrbProgramme = new JRadioButton[programme.length]; //Initialize an array with the number of programme

    private static int jrbIndex = -1;

    private static JList<String> jList = new JList<>(software);

    private static JButton jbtConfirm = new JButton("Confirm");
    private static JButton jbtClear = new JButton("Clear");
    private static JButton jbtExit = new JButton("Exit");

    public static void main(String[] args) {
        EventQueue.invokeLater(Practical6Q3::new);
    }

    private Practical6Q3() {

        JPanel jpnNorth = new JPanel();
        jpnNorth.setLayout(new GridLayout(2, 2));
        jpnNorth.add(new JLabel("Name"));
        jpnNorth.add(jtfName);
        jpnNorth.add(new JLabel("ID"));
        jpnNorth.add(jtfID);

        JPanel jpnWest = new JPanel();
        jpnWest.setLayout(new GridLayout(5, 1));
        jpnWest.add(new JLabel("Programme"));
        for (int i = 0; i < programme.length; i++) {
            jrbProgramme[i] = new JRadioButton(programme[i]); //Add name to object
            btg.add(jrbProgramme[i]); //Add object to group
            jpnWest.add(jrbProgramme[i]); //Add object to panel
            int finalI = i; //Finalize index for next line
            jrbProgramme[i].addActionListener(e -> {
                jrbIndex = finalI; //Once the button is pressed, it will change jrbIndex
            });
        }


        JPanel jpnEast = new JPanel();
        jpnEast.setLayout(new BoxLayout(jpnEast, BoxLayout.Y_AXIS));
        jpnEast.add(new JLabel("Software"));
        jpnEast.add(jList);


        JPanel jpnSouth = new JPanel();
        jpnSouth.setLayout(new FlowLayout());
        jpnSouth.add(jbtConfirm);
        jpnSouth.add(jbtClear);
        jpnSouth.add(jbtExit);

        add(jpnNorth, BorderLayout.NORTH);
        add(jpnWest, BorderLayout.WEST);
        add(new JScrollPane(jpnEast), BorderLayout.EAST);
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
            btg.clearSelection();
            jList.clearSelection();
        });
        jbtExit.addActionListener(e -> System.exit(0));

        setTitle("CheckOutSystem");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static void confirmListener() throws Exception {
        if (jtfName.getText().isEmpty())
            throw new Exception("Name field cannot be empty");
        if (jtfID.getText().isEmpty())
            throw new Exception("ID field cannot be empty");

        int[] jListSelectedIndices = jList.getSelectedIndices();

        if (jListSelectedIndices.length == 0) {
            throw new Exception("Software selection cannot be null");
        } //Check if there's at least one selected.


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Name : ").append(jtfName.getText()).append('\n')
                .append("ID : ").append(jtfID.getText()).append('\n')
                .append("Programme : ").append(programme[jrbIndex]).append('\n') //If -1, throw IndexOutOfBoundsException.
                .append("Software ------- ").append('\n');

        for (int jListSelectedIndice : jListSelectedIndices) {
            stringBuilder.append(software[jListSelectedIndice]).append('\n');
        }

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