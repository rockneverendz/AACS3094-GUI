package da;

import domain.Student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDA {
    private String tableName = "Student";
    private static Connection conn;
    private static PreparedStatement stmt;

    //Get connection to Programme Database
    //Since every student CRUD functions always pass getRecord,
    //There's really no reason to convert it into local var.
    private ProgrammeDA programmeDA = new ProgrammeDA();

    public StudentDA() {
        conn = DA.createConnection();
    }

    public Student getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        Student student = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getString("ID"),
                        rs.getString("IC"),
                        rs.getString("NAME"),
                        rs.getString("LEVEL").charAt(0),
                        programmeDA.getRecord(rs.getString("PROGRAMMECODE")),
                        rs.getInt("YR")
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return student;
    }

    public void addRecord(Student student) {
        String queryStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getIc());
            stmt.setString(3, student.getName());
            stmt.setString(4, String.format("%c", student.getLevel()));
            stmt.setString(5, student.getProgramme().getCode());
            stmt.setInt(6, student.getYear());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Student successful created.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void updateRecord(Student student) {

        String updateStr = "UPDATE " + tableName + " SET IC = ?, NAME = ?, LEVEL = ?, PROGRAMMECODE = ?, YR = ? WHERE ID = ?";

        try {

            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, student.getIc());
            stmt.setString(2, student.getName());
            stmt.setString(3, String.format("%c", student.getLevel()));
            stmt.setString(4, student.getProgramme().getCode());
            stmt.setInt(5, student.getYear());
            stmt.setString(6, student.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Student " + student.getId() + " has been updated."
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRecord(Student student) {

        String delStr = "DELETE FROM " + tableName + " WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(delStr);
            stmt.setString(1, student.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "The programme \"" + student.getId() + "\" has been deleted.",
                    null, JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void shutDown() {
        DA.shutDown(conn);
    }
}
