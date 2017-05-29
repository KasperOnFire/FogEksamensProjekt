package MaterialList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatShed {

    private static Map<String, Part> shedMap = new HashMap<>();
    private Part p;

    private int findPrice(ArrayList<Material> a, String s) {
        for (Material m : a) {
            if (m.getName().equals(s)) {
                return m.getPrice();
            }
        }
        return 2;
    }

    public Map<String, Part> calcShed(int length, int depth, ArrayList a) throws Exception {
        p = new Part(shedBoards(length, depth), findPrice(a, "Skurbræt"));
        shedMap.put("Skurbræt", p);

        p = new Part(shedNails(shedBoards(length, depth)), findPrice(a, "Skursøm"));
        shedMap.put("Skursøm", p);

        p = new Part(4, findPrice(a, "Løsholte"), length);
        shedMap.put("Løsholte, side", p);

        p = new Part(6, findPrice(a, "Løsholte"), depth);
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
