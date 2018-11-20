package ui;

import control.MaintainProgrammeControl;
import da.ProgrammeDA;
import domain.Programme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintainProgrammeFrame extends JFrame {

    private MaintainProgrammeControl progControl;
    private JTextField jtfCode = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfFaculty = new JTextField();

    private MaintainProgrammeFrame() {
        progControl = new MaintainProgrammeControl();

        JPanel jpCenter = new JPanel(new GridLayout(3, 2));
        jpCenter.add(new JLabel("Programme Code"));
        jpCenter.add(jtfCode);
        jpCenter.add(new JLabel("Programme Name"));
        jpCenter.add(jtfName);
        jpCenter.add(new JLabel("Faculty"));
        jpCenter.add(jtfFaculty);
        add(jpCenter);

        JPanel jpSouth = new JPanel();
        JButton jbtAdd = new JButton("Create");
        jpSouth.add(jbtAdd);
        JButton jbtRetrieve = new JButton("Retrieve");
        jpSouth.add(jbtRetrieve);
        JButton jbtUpdate = new JButton("Update");
        jpSouth.add(jbtUpdate);
        JButton jbtDelete = new JButton("Delete");
        jpSouth.add(jbtDelete);
        add(jpSouth, BorderLayout.SOUTH);

        jbtAdd.addActionListener(new AddListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            String name = jtfName.getText();
            String faculty = jtfFaculty.getText();

            Programme programme = progControl.selectRecord(code);

            if (programme != null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Code already exist",
                        "DUPLICATED CODE",
                        JOptionPane.ERROR_MESSAGE
                );
                reset();
                jtfCode.requestFocusInWindow();
            } else {
                programme = new Programme(code, name, faculty);
                progControl.addRecord(programme);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme programme = progControl.selectRecord(code);
            if (programme != null) {
                jtfName.setText(programme.getName());
                jtfFaculty.setText(programme.getFaculty());
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No such programme code.",
                        "RECORD NOT FOUND",
                        JOptionPane.ERROR_MESSAGE
                );
                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme programme = progControl.selectRecord(code);

            if (programme == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No such programme code.",
                        "RECORD NOT FOUND",
                        JOptionPane.ERROR_MESSAGE);
                reset();
                jtfCode.requestFocusInWindow();
            } else {
                String name = jtfName.getText();
                String faculty = jtfFaculty.getText();

                programme = new Programme(code, name, faculty);
                progControl.updateRecord(programme);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String code = jtfCode.getText();
            Programme programme = progControl.selectRecord(code);
            if (programme == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No such programme code.",
                        "RECORD NOT FOUND",
                        JOptionPane.ERROR_MESSAGE);
                reset();
                jtfCode.requestFocusInWindow();
            } else {
                int delRec = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this record?"
                );
                if (delRec == JOptionPane.YES_OPTION)
                    progControl.deleteRecord(programme);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private void reset() {
        jtfCode.setText("");
        jtfFaculty.setText("");
        jtfName.setText("");
    }

    public static void main(String[] args) {
        MaintainProgrammeFrame frm = new MaintainProgrammeFrame();
        frm.setTitle("Programme CRUD");
        frm.setSize(600, 200);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(ProgrammeDA::shutDown));
    }
}
