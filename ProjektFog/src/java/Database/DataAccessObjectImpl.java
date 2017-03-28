package Data;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import User.User;
import User.Password;
import Collection.Music;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector conn;
    Password pass = new Password();

    public DataAccessObjectImpl() throws Exception {
        this.conn = new DBConnector();
    }

    @Override
    public User getUserByName(String username) throws SQLException {
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("SELECT * FROM user WHERE username = (?);");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int UID = rs.getInt("UID");
                String usernameRetrieved = rs.getString("username");
                String passwordRetrieved = rs.getString("password");
                String saltRetrieved = rs.getString("salt");
                String emailRetrieved = rs.getString("email");
                String userString = rs.getString("userstring");

                user = new User(UID, usernameRetrieved, passwordRetrieved, saltRetrieved, emailRetrieved, userString);
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return user;
    }

    @Override
    public int getUIDByUserString(String userString) throws SQLException {
        int UID = 0;
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("SELECT UID FROM user WHERE userstring = (?);");
            stmt.setString(1, userString);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UID = rs.getInt("UID");
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        return UID;
    }

    @Override
    public ArrayList<Music> getAlbumByUID(int UID) throws SQLException {
        ArrayList<Music> albumCollection = new ArrayList();
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("SELECT * FROM music WHERE UID = (?);");
            stmt.setInt(1, UID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Music music = null;
                String identifier = rs.getString("identifier");
                String album = rs.getString("album");
                String artist = rs.getString("artist");
                music = new Music(identifier, artist, album);
                albumCollection.add(music);
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
        albumCollection.sort((o1, o2) -> o1.getArtist().compareTo(o2.getArtist()));
        return albumCollection;
    }

    @Override
    public void registerUser(String username, String password, String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement stmt = null;
        try {
            String passSalt = pass.getSaltString();
            stmt = conn.getConnection().prepareStatement("INSERT INTO user (username, email, password, salt, userstring) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, pass.get_SHA_512_SecurePassword(password, passSalt));
            stmt.setString(4, passSalt);
            stmt.setString(5, pass.getSaltString());
            stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean addAlbum(int UID, String artist, String album) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("INSERT INTO music (identifier, UID, artist, album) VALUES (?,?,?,?);");
            stmt.setString(1, getNewIdentifier());
            stmt.setInt(2, UID);
            stmt.setString(3, artist);
            stmt.setString(4, album);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private boolean checkIdentifier(String identifier) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("SELECT * FROM music WHERE identifier = (?);");
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private String getNewIdentifier() throws SQLException {
        boolean unique = false;
        String identifier = "";
        while (unique == false) {
            identifier = pass.getSaltString();
            if (checkIdentifier(identifier)) {
                unique = true;
            }
        }
        return identifier;
    }

    @Override
    public void removeAlbum(String identifier) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.getConnection().prepareStatement("DELETE FROM music WHERE identifier = (?);");
            stmt.setString(1, identifier);
            stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
