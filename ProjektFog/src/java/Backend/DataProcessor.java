package Backend;

import Database.*;
import java.sql.*;

import Carport.Base;
import Carport.Carport;
import Carport.Roof;
import Carport.Shed;
import org.json.JSONObject;

/**
 * This class exists to process the json that comes from the 3d render of the
 * carport, and to save it to the user.
 *
 * @author Kasper
 */
public class DataProcessor {

    DataAccessObjectImpl dao;

    /**
     *
     * @throws Exception
     */
    public DataProcessor() throws Exception {
        dao = new DataAccessObjectImpl();
    }

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
        int width = obj.getJSONObject("carport").getInt("width");
        int height = obj.getJSONObject("carport").getInt("height");
        int depth = obj.getJSONObject("carport").getInt("depth");
        Base b = new Base(width, depth, height);

        //roof
        boolean isGable = obj.getJSONObject("roof").getBoolean("gableRoof");
        int sides = obj.getJSONObject("roof").getJSONObject("overhang").getInt("sides");
        int front = obj.getJSONObject("roof").getJSONObject("overhang").getInt("front");
        int back = obj.getJSONObject("roof").getJSONObject("overhang").getInt("back");
        Roof r = new Roof(isGable, sides, front, back);

        //shed
        boolean hasShed = obj.getJSONObject("shed").getBoolean("shed");
        int depthShed = obj.getJSONObject("shed").getInt("depth");
        int doorPlacement = obj.getJSONObject("shed").getInt("doorPlacement");
        String side = obj.getJSONObject("shed").getString("side");
        boolean rotateDoor = obj.getJSONObject("shed").getBoolean("rotateDoor");
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
     * @param carport - the carport in json to save
     * @return status of the operation
     * @throws java.sql.SQLException if something goes wrong
     */
    public boolean saveCarportToUser(String username, String carport) throws SQLException {
        return dao.updateCarport(carport, username);
    }

    /**
     *
     * Gets the carport as json from the database
     *
     * @param userString the user to get it from
     * @return the carport in json
     */
    public String getCarportFromUser(String userString) {
        return dao.getCarport(userString);
    }
}
