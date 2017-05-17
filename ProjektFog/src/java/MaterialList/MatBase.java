/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialList;

import Carport.Shed;
import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kristian
 */
public class MatBase
{
    private DataAccessObject dao;
    private static Map<String, Part> baseMap = new HashMap<>();
    private Part p;
    
    private Map<String, Part> calcBase(int length, int depth, int height, Shed s)
    {
        p = new Part(posts(length,depth,s),dao.getDouble("price", "material", "name", "Stolpe"),height+90);
        baseMap.put("Stolper", p);
        
        p = new Part(postScrews(posts(length,depth,s)),dao.getDouble("price", "material", "name", "Stolpeskrue"));
        baseMap.put("Stolper", p);
        
        p = new Part(postBolts(posts(length,depth,s)),dao.getDouble("price", "material", "name", "Stolpebolt"));
        baseMap.put("Stolper", p);
        
        p = new Part(2,dao.getDouble("price", "material", "name", "Tagrem"),depth);
        baseMap.put("Tagrem", p);
        
        return baseMap;
    }
    
    private int posts(int length, int width, Shed s) {
        double m2 = (double)(width) * (length) / 2;
        int posts = (int)Math.ceil(m2 / 4) * 2;
        if (posts <= 3) {
            posts = 4;
        }
        if(s.isHasShed())
        {
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
