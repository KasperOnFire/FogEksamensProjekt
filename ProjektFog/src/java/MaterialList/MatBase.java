package MaterialList;

import Carport.Shed;
import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MatBase {

    private DataAccessObject dao;
    private static Map<String, Part> baseMap = new HashMap<>();
    private Part p;
    
    private double getPrice(String name) throws Exception
    {
        DatabaseBack DBB = new DatabaseBack();
        return DBB.getDouble(name);
    }

    public Map<String, Part> calcBase(int length, int depth, int height, Shed s) throws Exception {
        p = new Part(posts(length, depth, s), getPrice("Stolpe"), height + 90);
        baseMap.put("Stolper", p);

        p = new Part(postScrews(posts(length, depth, s)), getPrice("Stolpeskrue"));
        baseMap.put("Stolper", p);

        p = new Part(postBolts(posts(length, depth, s)), getPrice("Stolpebolt"));
        baseMap.put("Stolper", p);

        p = new Part(2, getPrice("Tagrem"), depth);
        baseMap.put("Tagrem", p);

        return baseMap;
    }

    private int posts(int length, int width, Shed s) {
        double m2 = (double) width * length;
        int posts = (int) Math.ceil(m2 / 2 / 4) * 2;
        if (posts <= 3) {
            posts = 4;
        }
        if (s.isHasShed()) {
            posts = posts + 1;
        }
        return posts;
    }

    private int postScrews(int posts) {
        return posts * 4;
    }

    private int postBolts(int posts) {
        return posts * 2;
    }

}
