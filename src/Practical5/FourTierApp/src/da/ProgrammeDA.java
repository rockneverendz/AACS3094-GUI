package da;

import domain.Programme;
import java.sql.*;
import javax.swing.*;

public class ProgrammeDA {
    private String host = "jdbc:derby://localhost:1527/collegedb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Programme";
    private Connection conn;
    private PreparedStatement stmt;
    
    public ProgrammeDA() {
        createConnection();
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return programme;
    }
    
        
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void shutDown() {
        if (conn != null)
            try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        ProgrammeDA da = new ProgrammeDA();
        Programme programme = da.getRecord("IA");
        System.out.println(programme);
    }
}
