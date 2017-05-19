package MaterialList;

import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MatShed {

    private DataAccessObject dao;
    private static Map<String, Part> shedMap = new HashMap<>();
    private Part p;
    
    private double getPrice(String name) throws Exception
    {
        DatabaseBack DBB = new DatabaseBack();
        return DBB.getDouble(name);
    }

    public Map<String, Part> calcShed(int length, int depth) throws Exception
    {
        p = new Part(shedBoards(length, depth),getPrice("Skurbræt"));
        shedMap.put("Skurbræt", p);
        
        p = new Part(shedNails(shedBoards(length, depth)),getPrice("Skursøm"));
        shedMap.put("Skursøm", p);
        
        p = new Part(4,getPrice("Løsholte"),length);
        shedMap.put("Løsholte, side", p);
        
        p = new Part(6,getPrice("Løsholte"),depth);
        shedMap.put("Løsholte, gavl", p);
        
        return shedMap;
    }

    private int shedBoards(int length, int depth) {
        return (((length * 2 + depth * 2) - 40) / 6) + 1;
    }

    private int shedNails(int shedBoards) {
        return (int) Math.ceil(shedBoards / 2) * 9;
    }
}
