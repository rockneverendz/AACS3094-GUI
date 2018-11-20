import java.sql.*;

public class AuthExampleEmbedded {

    public static void main(String[] args) {

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName="sampleCollege";
        String connectionURL = "jdbc:derby:" + dbName; // + ";create=true"
        Connection conn = null;

        // Load the driver
        try {
            Class.forName(driver);
            System.out.println(driver + " loaded.");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("\n Make sure your CLASSPATH variable " +
                    "contains %DERBY_HOME%\\lib\\derby.jar (${DERBY_HOME}/lib/derby.jar). \n");
        }

        // Start the database and set up users, then close database
        try {
            System.out.println("Trying to connect to " + connectionURL);
            conn = DriverManager.getConnection(connectionURL);
            System.out.println("Connected to database " + connectionURL);

            turnOnBuiltInUsers(conn);

            // shut down the database
            conn.close();
            System.out.println("Closed connection");

            /* In embedded mode, an application should shut down Derby.
               Shutdown throws the XJ015 exception to confirm success. */
            boolean gotSQLExc = false;
            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException se) {
                if ( se.getSQLState().equals("XJ015") ) {
                    gotSQLExc = true;
                }
            }
            if (!gotSQLExc) {
                System.out.println("Database did not shut down normally");
            } else {
                System.out.println("Database shut down normally");
            }

            // force garbage collection to unload the EmbeddedDriver
            //  so Derby can be restarted
            System.gc();
        } catch (Throwable e) {
            errorPrint(e);
            System.exit(1);
        }

        // Restart database and confirm that unauthorized users cannot
        //  access it
        connectionURL = "jdbc:derby:" + dbName;

        // Load the driver again
        try {
            Class.forName(driver).newInstance();
            System.out.println(driver + " loaded.");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("\n Make sure your CLASSPATH variable " +
                    "contains %DERBY_HOME%\\lib\\derby.jar (${DERBY_HOME}/lib/derby.jar). \n");
        } catch (Exception ee) {
            errorPrint(ee);
        }

        // Try to log in with no username or password
        try {
            // this should fail
            System.out.println("Trying to connect to " + connectionURL +
                    " without username or password");
            conn = DriverManager.getConnection(connectionURL);
            System.out.println("Connected to database " + dbName);

            // if it doesn't, close statement and connection
            conn.close();
            System.out.println("Closed connection");
        } catch (Throwable e) {
            errorPrint(e);
        }

        // Log in as a user with read-only access
        try {
            // connection should succeed, but create table should fail
            String newURL = connectionURL + ";user=guest;password=java5w6x";
            System.out.println("Trying to connect to " + newURL);
            conn = DriverManager.getConnection(newURL);
            System.out.println("Connected to database " + dbName +
                    " with read-only access");

            Statement s = conn.createStatement();
            s.executeUpdate("CREATE TABLE t1(C1 VARCHAR(6))");

            // if it doesn't, close statement and connection
            s.close();
            conn.close();
            System.out.println("Closed connection");
        } catch (Throwable e) {
            errorPrint(e);
        }

        // Log in as a user with full access
        // Create, update, and query table
        try {
            // this should succeed
            String newURL = connectionURL + ";user=mary;password=little7xylamb";
            System.out.println("Trying to connect to " + newURL);
            conn = DriverManager.getConnection(newURL);
            System.out.println("Connected to database " + dbName);

            Statement s = conn.createStatement();
            s.executeUpdate("CREATE TABLE T1(C1 VARCHAR(6))");
            System.out.println("Created table T1");
            s.executeUpdate("INSERT INTO T1 VALUES('hello')");

            ResultSet rs = s.executeQuery("SELECT * FROM T1");
            rs.next();
            System.out.println("Value of T1/C1 is " + rs.getString(1));

            s.close();
        } catch (Throwable e) {
            errorPrint(e);
        }

        // Remove table, then remove users previously created
        // If you don't remove the table, you will never be able to find it,
        //  because it was created by a user who no longer exists and with a
        //  default connection mode of noAccess
        try {
            //Statement s = conn.createStatement();
            //s.executeUpdate("DROP TABLE T1");
            //System.out.println("Removed table T1");
            //s.close();

            turnOffBuiltInUsers(conn);

            conn.close();
            System.out.println("Closed connection");

            // Shut down the database
            /* In embedded mode, an application should shut down Derby.
               Shutdown throws the XJ015 exception to confirm success. */
            boolean gotSQLExc = false;
            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException se) {
                if ( se.getSQLState().equals("XJ015") ) {
                    gotSQLExc = true;
                }
            }
            if (!gotSQLExc) {
                System.out.println("Database did not shut down normally");
            } else {
                System.out.println("Database shut down normally");
            }
        } catch (Throwable e) {
            errorPrint(e);
        }
    }

    /**
     * Turn on built-in user authentication and user authorization.
     *
     * @param conn a connection to the database.
     */
    public static void turnOnBuiltInUsers(Connection conn) throws SQLException {
        System.out.println("Turning on authentication.");
        Statement s = conn.createStatement();

        // Setting and Confirming requireAuthentication
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.connection.requireAuthentication', 'true')");
        ResultSet rs = s.executeQuery(
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.connection.requireAuthentication')");
        rs.next();
        System.out.println("Value of requireAuthentication is " +
                rs.getString(1));
        // Setting authentication scheme to Derby
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.authentication.provider', 'BUILTIN')");

        // Creating some sample users
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.sa', 'ajaxj3x9')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.guest', 'java5w6x')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.mary', 'little7xylamb')");

        // Setting default connection mode to no access
        // (user authorization)
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.defaultConnectionMode', 'noAccess')");
        // Confirming default connection mode
        rs = s.executeQuery (
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.database.defaultConnectionMode')");
        rs.next();
        System.out.println("Value of defaultConnectionMode is " +
                rs.getString(1));

        // Defining read-write users
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.fullAccessUsers', 'sa,mary')");

        // Defining read-only users
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.readOnlyAccessUsers', 'guest')");

        // Confirming full-access users
        rs = s.executeQuery(
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.database.fullAccessUsers')");
        rs.next();
        System.out.println("Value of fullAccessUsers is " + rs.getString(1));

        // Confirming read-only users
        rs = s.executeQuery(
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.database.readOnlyAccessUsers')");
        rs.next();
        System.out.println("Value of readOnlyAccessUsers is " +
                rs.getString(1));

        // We would set the following property to TRUE only
        // when we were ready to deploy.
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.propertiesOnly', 'false')");
        s.close();
    }

    /**
     * Turn off built-in user authentication and user authorization.
     *
     * @param conn a connection to the database.
     */
    public static void turnOffBuiltInUsers (Connection conn) throws SQLException {
        Statement s = conn.createStatement();
        System.out.println("Turning off authentication.");

        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.connection.requireAuthentication', 'false')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.authentication.provider', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.sa', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.guest', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.user.mary', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.defaultConnectionMode', 'fullAccess')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.fullAccessUsers', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.readOnlyAccessUsers', null)");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                "'derby.database.propertiesOnly', 'false')");

        // Confirming requireAuthentication
        ResultSet rs = s.executeQuery(
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.connection.requireAuthentication')");
        rs.next();
        System.out.println("Value of requireAuthentication is " +
                rs.getString(1));

        // Confirming default connection mode
        rs = s.executeQuery(
                "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
                        "'derby.database.defaultConnectionMode')");
        rs.next();
        System.out.println("Value of defaultConnectionMode is " +
                rs.getString(1));
        System.out.println("Turned off all the user-related properties");
        s.close();
    }

    /** Exception reporting methods
     *   with special handling of SQLExceptions
     */
    static void errorPrint(Throwable e) {
        if (e instanceof SQLException)
            SQLExceptionPrint((SQLException)e);
        else {
            System.out.println("A non-SQL error occurred.");
            e.printStackTrace();
        }
    }  // END errorPrint

    //  Iterates through a stack of SQLExceptions
    static void SQLExceptionPrint(SQLException sqle) {
        while (sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState:   " + (sqle).getSQLState());
            System.out.println("Severity: " + (sqle).getErrorCode());
            System.out.println("Message:  " + (sqle).getMessage());
            sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }  //  END SQLExceptionPrint
}
