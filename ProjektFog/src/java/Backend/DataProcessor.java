/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Carport.Base;
import Carport.Carport;
import Carport.Roof;
import Carport.Shed;
import org.json.JSONObject;

/**
 *
 * @author Kasper
 */
public class DataProcessor {

    public Carport parseJson(String json) {
        JSONObject obj = new JSONObject(json);

        //base
        int width = obj.getJSONObject("guiCarport").getInt("width");
        int height = obj.getJSONObject("guiCarport").getInt("height");
        int depth = obj.getJSONObject("guiCarport").getInt("depth");
        Base b = new Base(width, depth, height);

        //roof
        boolean isGable = obj.getJSONObject("guiRoof").getBoolean("gableRoof");
        int sides = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("sides");
        int front = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("front");
        int back = obj.getJSONObject("guiRoof").getJSONObject("overhang").getInt("back");
        Roof r = new Roof(isGable, sides, front, back);

        //shed
        boolean hasShed = obj.getJSONObject("guiShed").getBoolean("shed");
        int depthShed = obj.getJSONObject("guiShed").getInt("depth");
        int doorPlacement = obj.getJSONObject("guiShed").getInt("doorPlacement");
        String side = obj.getJSONObject("guiShed").getString("side");
        boolean rotateDoor = obj.getJSONObject("guiShed").getBoolean("rotateDoor");
        Shed s = new Shed(hasShed, depthShed, doorPlacement, side, rotateDoor);

        //Carport
        Carport c = new Carport(b, r, s);
        if (c.getBase().getDepth() != 0) {
            return c;
        } else {
            return null;
        }
    }
    
    public void saveCarportToUser(String userstring, Carport c){
        
    }
}
