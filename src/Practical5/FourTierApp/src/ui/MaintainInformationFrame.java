package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintainInformationFrame extends JFrame {
    private MaintainInformationFrame(){
        GridLayout gridlayout = new GridLayout(2,1,10,10);
        this.setLayout(gridlayout);

        JButton jbtStudent = new JButton("Maintain Student");
        JButton jbtProgramme = new JButton("Maintain Programme");

        add(jbtProgramme);
        add(jbtStudent);

        jbtProgramme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintainProgrammeFrame.main();
                setVisible(false);
            }
        });

        jbtStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintainStudentFrame.main();
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        MaintainInformationFrame frm = new MaintainInformationFrame();
        frm.setTitle("Maintain Information");
        frm.setSize(300, 200);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
}
