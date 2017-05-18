package MaterialList;

import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MatShed {

    private DataAccessObject dao;
    private static Map<String, Part> shedMap = new HashMap<>();
    private Part p;

    public Map<String, Part> calcShed(int length, int depth) {
        p = new Part(shedBoards(length, depth), dao.getDouble("price", "material", "name", "Skurbræt"));
        shedMap.put("Skurbræt", p);

        p = new Part(shedNails(shedBoards(length, depth)), dao.getDouble("price", "material", "name", "Skursøm"));
        shedMap.put("Skursøm", p);

        p = new Part(4, dao.getDouble("price", "material", "name", "Løsholte"), length);
        shedMap.put("Løsholte, side", p);

        p = new Part(6, dao.getDouble("price", "material", "name", "Løsholte"), depth);
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
