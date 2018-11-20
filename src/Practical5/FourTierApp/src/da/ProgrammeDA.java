package da;

import domain.Programme;

import javax.swing.*;
import java.sql.*;

public class ProgrammeDA {
    private String tableName = "Programme";
    private static Connection conn;
    private static PreparedStatement stmt;

    public ProgrammeDA() {
        conn = DA.createConnection();
    }

    public Programme getRecord(String code) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE Code = ?";
        Programme programme = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                programme = new Programme(code, rs.getString("Name"), rs.getString("Faculty"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return programme;
    }

    public void addRecord(Programme progm) {
        String queryStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, progm.getCode());
            stmt.setString(2, progm.getName());
            stmt.setString(3, progm.getFaculty());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Programme successful created.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void updateRecord(Programme progm) {

        String updateStr = "UPDATE " + tableName + " SET Name = ?, Faculty = ? WHERE Code = ?";

        try {

            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, progm.getName());
            stmt.setString(2, progm.getFaculty());
            stmt.setString(3, progm.getCode());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Programme " + progm.getCode() + " has been updated.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void deleteRecord(Programme progm) {

        String delStr = "DELETE FROM " + tableName + " WHERE Code = ?";
        try {
            stmt = conn.prepareStatement(delStr);
            stmt.setString(1, progm.getCode());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "The programme \"" + progm.getName() + "\" has been deleted.", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void shutDown() {
        DA.shutDown(conn);
    }

    public static void main(String[] args) {
        ProgrammeDA da = new ProgrammeDA();
        Programme programme = da.getRecord("IA");
        System.out.println(programme);
    }
}
