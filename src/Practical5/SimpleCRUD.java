import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SimpleCRUD extends JFrame {
    private String tableName = "Programme";
    private static Connection conn;
    private static PreparedStatement stmt;

    private JTextField jtfCode = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfFaculty = new JTextField();

    private SimpleCRUD() {
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

        createConnection();

    }

    private ResultSet selectRecord(String code) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE Code = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = selectRecord((jtfCode.getText()));
                if (!rs.next()) {
                    stmt = conn.prepareStatement("INSERT INTO " + tableName + " VALUES ( ?, ?, ?)");
                    stmt.setString(1, jtfCode.getText());
                    stmt.setString(2, jtfName.getText());
                    stmt.setString(3, jtfFaculty.getText());
                    stmt.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Existing record found.",
                            "DUPLICATE RECORD",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class RetrieveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = selectRecord(jtfCode.getText());
                if (rs.next()) {
                    jtfName.setText(rs.getString("Name"));
                    jtfFaculty.setText(rs.getString("Faculty"));
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No such programme code.",
                            "RECORD NOT FOUND",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = selectRecord(jtfCode.getText());
                if (rs.next()) {
                    stmt = conn.prepareStatement("UPDATE " + tableName + " SET NAME = ?, FACULTY = ? WHERE CODE = ?");
                    stmt.setString(1, jtfName.getText());
                    stmt.setString(2, jtfFaculty.getText());
                    stmt.setString(3, jtfCode.getText());

                    stmt.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No such programme code.",
                            "RECORD NOT FOUND",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = selectRecord(jtfCode.getText());
                if (rs.next()) {
                    stmt = conn.prepareStatement("DELETE from " + tableName + " where CODE = ?");
                    stmt.setString(1, jtfCode.getText());
                    stmt.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No such programme code.",
                            "RECORD NOT FOUND",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createConnection() {
        try {
            //String host = "jdbc:derby:D:/Repository/GUITutorial/src/Practical5/collegeDB";
            String host = "jdbc:derby:collegeDB";
            String user = "";
            String password = "";
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void shutDown() {
        if (conn != null)
            try {
                conn.close();
                System.out.print("Successfully Closed Connection.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                System.out.print("Error in closing connection.");
            }
    }

    public static void main(String[] args) {
        SimpleCRUD frm = new SimpleCRUD();
        frm.setTitle("Programme CRUD");
        frm.setSize(600, 200);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(SimpleCRUD::shutDown));
    }
}
