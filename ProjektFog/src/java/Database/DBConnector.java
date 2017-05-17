package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for connection to MySQL database.
 *
 */
public class DBConnector {

    private Connection connection = null;

    private static final String IP = "46.101.97.181";
    private static final int PORT = 3306;

    /**
     *the database/Schema to use on the MySQL server.
     */
    public static final String DATABASE = "fogdb";
    private static final String USERNAME = "servletuser";
    private static final String PASSWORD = "ServletSucks";

    /**
     *
     * Creates the connection to the database.
     *
     * @throws ClassNotFoundException if jdbc driver is not configured
     * @throws InstantiationException if it fails to instantiate an object
     * @throws IllegalAccessException if the password/username is wrong
     * @throws SQLException if an sql statement is not valid sql
     */
    public DBConnector() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
        this.connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    /**
     * gived the Connection object of the database connetion.
     *
     * @return the connection to the database.
     */
    public Connection getConnection() {
        return this.connection;
    }
}
