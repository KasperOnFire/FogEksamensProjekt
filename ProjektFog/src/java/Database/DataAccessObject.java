package Database;

public interface DataAccessObject {
    
    public double getDouble(String var, String table, String term, String termName);
    
    public int getInt(String var, String table, String term, String termName);
    
    public String getString(String var, String table, String term, String termName);
}
