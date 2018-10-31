import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Practical2Q4 extends JFrame {
    private ArrayList<String> studentList = new ArrayList<String>();

    public static void main(String[] args) {
        new Practical2Q4();
    }

    private Practical2Q4() {
        studentList.add("Jack Ryan");
        studentList.add("Anne Matahway");
        studentList.add("Marion Lee");
        studentList.add("Walter Matthau");

        setLayout(new FlowLayout());

        JButton jbtCreate = new JButton("Create");
        add(jbtCreate);
        JButton jbtRetrieve = new JButton("Retrieve");
        add(jbtRetrieve);
        JButton jbtUpdate = new JButton("Update");
        add(jbtUpdate);
        JButton jbtDelete = new JButton("Delete");
        add(jbtDelete);

        pack();

        jbtCreate.addActionListener(e ->
                {
                    String in = JOptionPane.showInputDialog(
                            null,
                            "Enter new student's name",
                            "Input",
                            JOptionPane.QUESTION_MESSAGE);
                    if (in != null) {
                        studentList.add(in);
                    }
                }
        );

        jbtRetrieve.addActionListener(e -> {
            String finalString = retrieveStudentList();
            JOptionPane.showMessageDialog(
                    null,
                    finalString,
                    "Retrieve",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        jbtUpdate.addActionListener(e -> {
            int stdIndex;
            do {
                stdIndex = retrieveStudentIndex();
                System.out.println(stdIndex);
            } while (stdIndex == -1);
            if (stdIndex != 0) {
                try {
                    stdIndex--;
                    studentList.set(stdIndex, (String) JOptionPane.showInputDialog(
                            null,
                            "Current student name is " + studentList.get(stdIndex) + ".\nEnter updated name:",
                            "Update",
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            null,
                            studentList.get(stdIndex)));

                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid index number",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    //System.out.print(ex);
                }
            }
        });

        jbtDelete.addActionListener(e -> {
            int stdIndex;
            do {
                stdIndex = retrieveStudentIndex();
                System.out.println(stdIndex);
            } while (stdIndex == -1);
            if (stdIndex != 0) {
                try {
                    stdIndex--;
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            studentList.get(stdIndex) + " will be deleted. Continue?",
                            "Delete",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (n == JOptionPane.OK_OPTION) {
                        studentList.remove(stdIndex);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid index number",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    //System.out.print(ex);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String retrieveStudentList() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < studentList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(studentList.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }

    private int retrieveStudentIndex() {
        String finalString = retrieveStudentList();
        int inInt;
        String inString;
        try {
            inString = JOptionPane.showInputDialog(
                    null,
                    finalString,
                    "Select",
                    JOptionPane.QUESTION_MESSAGE);
            if (inString == null) return 0;
            inInt = Integer.parseInt(inString);
        } catch (NumberFormatException ex) {
            inInt = -1;
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a valid integer.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return inInt;
    }
}