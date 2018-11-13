import java.sql.*;

public class SimpleSelect {      
      
  public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
    // load the JDBC driver
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    System.out.println("Driver loaded...\n");
        
    // establish a connection
    Connection conn = DriverManager.getConnection("jdbc:odbc:TARCollegeDS");        
    System.out.println("College database connected...\n");
        
    // create a statement
    Statement stmt = conn.createStatement();
        
    // execute a statement
    ResultSet rs = stmt.executeQuery("SELECT * FROM Student;");
        
    // loop through the ResultSet and print out student details
    while(rs.next()){
      System.out.println("Student ID     : " + rs.getString(1));
      System.out.println("Student Name   : " + rs.getString(2));
      System.out.println("Course ID      : " + rs.getString("CourseID"));
      System.out.println("Year Of Studies: " + rs.getInt("YearOfStudies"));
      System.out.println("Tutorial Group : " + rs.getString("TutorialGroup"));
      System.out.println("");            
    }                
  } // end main
} // end class
