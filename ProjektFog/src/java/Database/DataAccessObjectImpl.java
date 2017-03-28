package Database;

public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector conn;

    public DataAccessObjectImpl() throws Exception {
        this.conn = new DBConnector();
    }

}
