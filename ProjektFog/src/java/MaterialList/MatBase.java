package MaterialList;

import Carport.Shed;
import Database.DataAccessObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatBase {

    private static Map<String, Part> baseMap = new HashMap<>();
    private Part p;
    
    private int findPrice(ArrayList<Material> a, String s)
    {
        for (Material m:a)
        {
            if(m.getName().equals(s))
            {
                return m.getPrice();
            }
        }
        return 2;
    }    

    public Map<String, Part> calcBase(int length, int depth, int height, Shed s, ArrayList a) throws Exception {
        p = new Part(posts(length, depth, s), findPrice(a, "Stolpe"), height + 90);
        baseMap.put("Stolper", p);

        p = new Part(postScrews(posts(length, depth, s)), findPrice(a, "Stolpeskrue"));
        baseMap.put("Stolper", p);

        p = new Part(postBolts(posts(length, depth, s)), findPrice(a, "Stolpebolt"));
        baseMap.put("Stolper", p);

        p = new Part(2, findPrice(a, "Tagrem"), depth);
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
