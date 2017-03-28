package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBConnector {
        private Connection connection = null;

        private static final String IP	     = "localhost";
        private static final int    PORT     = 3306;
        public static final String DATABASE  = "collection";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "root";	     	

        public DBConnector() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
                this.connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
        }

        public Connection getConnection() {
                return this.connection;
        }
    }