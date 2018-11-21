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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practical6Q1 extends JFrame {
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

    private static JRadioButton[] jrbProgramme = new JRadioButton[programme.length]; //Initialize an array with the number of programme
    private static JCheckBox[] jcbSoftware = new JCheckBox[software.length]; //Initialize an array with the number of software

    private static int jrbIndex = -1;

    private static JButton jbtConfirm = new JButton("Confirm");
    private static JButton jbtClear = new JButton("Clear");
    private static JButton jbtExit = new JButton("Exit");


    public static void main(String[] args) {
        EventQueue.invokeLater(Practical6Q1::new);
    }

    private Practical6Q1() {

        JPanel jpnNorth = new JPanel();
        jpnNorth.setLayout(new GridLayout(2, 2));
        jpnNorth.add(new JLabel("Name"));
        jpnNorth.add(jtfName);
        jpnNorth.add(new JLabel("ID"));
        jpnNorth.add(jtfID);

        JPanel jpnWest = new JPanel();
        jpnWest.setLayout(new GridLayout(5, 1));
        jpnWest.add(new JLabel("Programme"));
        ButtonGroup btg = new ButtonGroup();
        for (int i = 0; i < programme.length; i++) {
            jrbProgramme[i] = new JRadioButton(programme[i]); //Add name to object
            btg.add(jrbProgramme[i]); //Add object to group
            jpnWest.add(jrbProgramme[i]); //Add object to panel
            int finalI = i; //Finalize index for next line
            jrbProgramme[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jrbIndex = finalI; //Once the button is pressed, it will change jrbIndex
                }
            });
        }


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

        jbtConfirm.addActionListener(new confirmListener());

        setTitle("CheckOutSystem");
        //setSize(600, 200);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class confirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(jtfName.getText());
            System.out.println(jtfID.getText());
            System.out.println(jrbIndex);
            for (JCheckBox jcb : jcbSoftware){
                System.out.println(jcb.isSelected());
            }

        }
    }
}