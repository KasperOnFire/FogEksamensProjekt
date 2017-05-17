/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialList;

import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kristian
 */
public class MatShed
{
    private DataAccessObject dao;
    private static Map<String, Part> shedMap = new HashMap<>();
    private Part p;
    
    private Map<String, Part> calcShed(int length, int depth)
    {
        p = new Part(shedBoards(length, depth),dao.getDouble("price", "material", "name", "Skurbræt"));
        shedMap.put("Skurbræt", p);
        
        p = new Part(shedNails(shedBoards(length, depth)),dao.getDouble("price", "material", "name", "Skursøm"));
        shedMap.put("Skursøm", p);
        
        p = new Part(4,dao.getDouble("price", "material", "name", "Løsholte"),length);
        shedMap.put("Løsholte, side", p);
        
        p = new Part(6,dao.getDouble("price", "material", "name", "Løsholte"),depth);
        shedMap.put("Løsholte, gavl", p);
        
        return shedMap;
    }
    
    private int shedBoards(int length, int depth)
    {
        return (((length*2+depth*2)-40)/6)+1;
    }
    
    private int shedNails(int shedBoards)
    {
        return (int)Math.ceil(shedBoards/2)*9;
    }
}
