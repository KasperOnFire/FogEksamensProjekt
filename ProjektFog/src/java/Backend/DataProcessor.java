package Backend;

import Carport.*;
import org.json.*;

/**
 * This class exists to process the json that comes from the 3d render of the
 * carport, and to save it to the user.
 *
 * @author Kasper
 */
public class DataProcessor {

    /**
     * Takes a String of JSON that follows the datastructure in the Carport
     * class, and converts it to java object. non flexible method - only works
     * with one datastructure.
     *
     * @param json - the string to be converted to java.
     * @return the Carport with all the data from the JSON String.
     */
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

    /**
     *
     * The method that handles saving a carport to a specific user.
     *
     * @param username - the username to where the carport should be saved.
     * @param c the Carport to be saved in the database for the user.
     */
    public void saveCarportToUser(String username, Carport c) {

    }
}
