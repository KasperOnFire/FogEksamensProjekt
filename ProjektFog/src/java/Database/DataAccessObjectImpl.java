package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessObjectImpl implements DataAccessObject {

    private final DBConnector dbcon;
    private final Connection conn = null;

    public DataAccessObjectImpl() throws Exception {
        this.dbcon = new DBConnector();
    }

    public int getInt(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    
    public double getDouble(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    
    public String getString(String var, String table, String term, String termName) {
        String sql = "select ? from ? where ?=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, var);
            stmt.setString(2, table);
            stmt.setString(3, term);
            stmt.setString(4, termName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(var);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
