package Database;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface DataAccessObject {

    public int getUIDByUserString(String userString) throws SQLException;

    public void registerUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException;

    public boolean addAlbum(int UID, String artist, String album) throws SQLException;

    public void removeAlbum(String identifier) throws SQLException;
}
