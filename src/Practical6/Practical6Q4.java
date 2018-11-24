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
import java.util.ArrayList;

public class Practical6Q4 extends JFrame {
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
    private static JCheckBox[] jcbSoftware = new JCheckBox[software.length]; //Initialize an array with the number of software
    private static ArrayList<String> softwareSelected = new ArrayList<>();


    private static int jrbIndex = -1;

    private static JButton jbtConfirm = new JButton("Confirm");
    private static JButton jbtClear = new JButton("Clear");
    private static JButton jbtExit = new JButton("Exit");

    public static void main(String[] args) {
        EventQueue.invokeLater(Practical6Q4::new);
    }

    private Practical6Q4() {

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
        jpnEast.setLayout(new GridLayout(5, 1));
        jpnEast.add(new JLabel("Software"));
        for (int i = 0; i < software.length; i++) {
            jcbSoftware[i] = new JCheckBox(software[i]); //Add name to object
            jpnEast.add(jcbSoftware[i]); //Add object to panel
            int finalI = i;
            jcbSoftware[i].addActionListener(e -> {
                //If it is selected, add string to ArrayList. Otherwise, remove string from ArrayList.
                if (jcbSoftware[finalI].isSelected()) softwareSelected.add(software[finalI]);
                else softwareSelected.remove(software[finalI]);
            });
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
            }
        });
        jbtClear.addActionListener(e -> {
            jtfID.setText("");
            jtfName.setText("");
            btg.clearSelection();
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

    private static void confirmListener(){
        SoftwareCheckOut softwareCheckOut = new SoftwareCheckOut(
                jtfName.getText(),
                jtfID.getText(),
                programme[jrbIndex],
                softwareSelected
        );

        JFrame Window = new JFrame();
        Window.add(new SubWindow(softwareCheckOut));
        Window.setVisible(true);
        Window.setLocationRelativeTo(null);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.pack();
    }
}

class SubWindow extends JPanel {
    private static JTextPane jTextPane = new JTextPane();
    SubWindow(SoftwareCheckOut softwareCheckOut) {
        System.out.println(
                softwareCheckOut.getName() +
                        softwareCheckOut.getId() +
                        softwareCheckOut.getProgramme() +
                        softwareCheckOut.getSoftwareList()
        );
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Software Checkout Summary\n").append('\n')
                .append("Student Name : ").append(softwareCheckOut.getName()).append('\n')
                .append("Student Id : ").append(softwareCheckOut.getId()).append('\n')
                .append("Programme :  ").append(softwareCheckOut.getProgramme()).append('\n')
                .append("Software List --------------").append('\n');
        for (String string:softwareCheckOut.getSoftwareList()){
            stringBuilder.append(string).append('\n');
        }

        jTextPane.setText(stringBuilder.toString());
        jTextPane.setEditable(false);

        add(jTextPane);
    }
}