package Database;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector conn;

    public DataAccessObjectImpl() throws Exception {
        this.conn = new DBConnector();
    }

    
}
