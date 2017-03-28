package Data;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import User.User;
import Collection.Music;
import java.util.ArrayList;

public interface DataAccessObject {

    public User getUserByName(String username) throws SQLException;

    public int getUIDByUserString(String userString) throws SQLException;

    public ArrayList<Music> getAlbumByUID(int UID) throws SQLException;

    public void registerUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException;

    public boolean addAlbum(int UID, String artist, String album) throws SQLException;

    public void removeAlbum(String identifier) throws SQLException;
}
