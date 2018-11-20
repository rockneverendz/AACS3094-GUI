package ui;

import control.MaintainStudentControl;
import da.StudentDA;
import domain.Programme;
import domain.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintainStudentFrame extends JFrame {

    private MaintainStudentControl studentControl;
    private JTextField jtfID = new JTextField();
    private JTextField jtfIC = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfLevel = new JTextField();
    private JTextField jtfCode = new JTextField();
    private JTextField jtfYear = new JTextField();

    private MaintainStudentFrame() {
        studentControl = new MaintainStudentControl();

        JPanel jpCenter = new JPanel(new GridLayout(6, 2));
        jpCenter.add(new JLabel("Student ID"));
        jpCenter.add(jtfID);
        jpCenter.add(new JLabel("Student IC"));
        jpCenter.add(jtfIC);
        jpCenter.add(new JLabel("Student Name"));
        jpCenter.add(jtfName);
        jpCenter.add(new JLabel("Level"));
        jpCenter.add(jtfLevel);
        jpCenter.add(new JLabel("Programme Code"));
        jpCenter.add(jtfCode);
        jpCenter.add(new JLabel("Year"));
        jpCenter.add(jtfYear);
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
            String studID = jtfID.getText();
            String studCode = jtfCode.getText();

            Student student = studentControl.selectRecord(studID);
            Programme programme = new Programme(studCode);

            if (student != null) { //Record exists
                JOptionPane.showMessageDialog(
                        null,
                        "Code already exist",
                        "DUPLICATED CODE",
                        JOptionPane.ERROR_MESSAGE
                );
                reset();
                jtfCode.requestFocusInWindow();
            } else { //Record does not exists
                String studIC = jtfIC.getText();
                String studName = jtfName.getText();
                char studLevel = jtfLevel.getText().charAt(0);
                int studYear = Integer.parseInt(jtfYear.getText());


                student = new Student(studID, studIC, studName, studLevel, programme, studYear);
                studentControl.addRecord(student);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = jtfID.getText();
            Student student = studentControl.selectRecord(studentID);
            if (student != null) { //Record found
                jtfID.setText(student.getId());
                jtfIC.setText(student.getIc());
                jtfName.setText(student.getName());
                jtfLevel.setText(String.format("%c", student.getLevel()));
                jtfCode.setText(student.getProgramme().getCode());
                jtfYear.setText(String.format("%d", student.getYear()));
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No such student ID.",
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
            String studID = jtfID.getText();
            String studCode = jtfCode.getText();

            Student student = studentControl.selectRecord(studID);
            Programme programme = new Programme(studCode);

            if (student == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No such student ID.",
                        "RECORD NOT FOUND",
                        JOptionPane.ERROR_MESSAGE
                );
                reset();
                jtfCode.requestFocusInWindow();
            } else {
                String studIC = jtfIC.getText();
                String studName = jtfName.getText();
                char studLevel = jtfLevel.getText().charAt(0);
                int studYear = Integer.parseInt(jtfYear.getText());

                student = new Student(studID, studIC, studName, studLevel, programme, studYear);
                studentControl.updateRecord(student);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = jtfID.getText();
            Student student = studentControl.selectRecord(studentID);
            if (student == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No such student ID.",
                        "RECORD NOT FOUND",
                        JOptionPane.ERROR_MESSAGE
                );
                reset();
                jtfCode.requestFocusInWindow();
            } else {
                int delRec = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this record?"
                );
                if (delRec == JOptionPane.YES_OPTION)
                    studentControl.deleteRecord(student);

                reset();
                jtfCode.requestFocusInWindow();
            }
        }
    }

    private void reset() {
        jtfID.setText("");
        jtfIC.setText("");
        jtfName.setText("");
        jtfLevel.setText("");
        jtfCode.setText("");
        jtfYear.setText("");
    }


    public static void main(String[] args) {
        MaintainStudentFrame frm = new MaintainStudentFrame();
        frm.setTitle("Student CRUD");
        frm.setSize(600, 400);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(StudentDA::shutDown));
    }
}
