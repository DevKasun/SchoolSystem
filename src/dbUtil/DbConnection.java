package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final static String USERNAME = "dbuser";
    private final static String PASSWORD = "dbpassword";
    private final static String CONN = "jdbc:sqlite:school.sqlite";
    private final static String MYCONN = "jdbc:mysql://localhost/login";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONN);
        }

        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
