import java.sql.*;

public class DatabaseDefault {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Establish a connection
        Connection conn = DriverManager.getConnection(
                "jdbc:derby:D:/Repository/GUITutorial/src/Practical5/sampleCollege"
        );
        System.out.println("College database connected...\n");

        //Create a statement
        Statement stmt = conn.createStatement();

        //Execute a statement
        ResultSet rs = stmt.executeQuery("SELECT t.* FROM APP.TICKETS t");
        //ResultSet rs = stmt.executeQuery("SELECT * FROM TICKETS");

        //Loop through the ResultSet and print out student details
        while (rs.next()) {
            System.out.println("Student ID     : " + rs.getInt(1));
            System.out.println("Student Name   : " + rs.getInt(2));
            System.out.println("Course ID      : " + rs.getDouble(3));
            System.out.println();
        }

        //Shut down the database
        conn.close();
        System.out.println("Closed connection");

        //In embedded mode, an application should shut down Derby.
        //Shutdown throws the XJ015 exception to confirm success. */

        boolean gotSQLExc = false;
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
            if (se.getSQLState().equals("XJ015")) gotSQLExc = true;
        }
        if (!gotSQLExc) System.out.println("Database did not shut down normally");
        else System.out.println("Database shut down normally");

        //Force garbage collection to unload the EmbeddedDriver
        //So Derby can be restarted
        System.gc();
    }
}
