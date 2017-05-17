package MaterialList;

import Carport.Carport;
import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MaterialList {

    private int mountingBands = 2;
    private static Map<String, Part> matList = new HashMap<>();
    private DataAccessObject dao;
    private Part p;
    private MatRoof matRoof;
    private MatBase matBase;
    private MatShed matShed;


    public int totalPriceRounded() {
        double totalPrice = 0;
        for (Map.Entry<String, Part> part : matList.entrySet()) {
            totalPrice += part.getValue().getAmount() * part.getValue().getPrice();
        }
        double totalAdjustedPrice = Math.ceil(totalPrice / 1000) * 1000;
        return (int) totalAdjustedPrice;
    }
    
    public void calcMaterialList(Carport c) {
        int length;
        if(c.getShed().isHasShed())
        {
            length = c.getBase().getDepth()+c.getShed().getDepth()+c.getRoof().getFront()+c.getRoof().getBack();
        }
        else
        {
            length = c.getBase().getDepth()+c.getRoof().getFront()+c.getRoof().getBack();
        }
        int width = c.getBase().getWidth()+c.getRoof().getSides()+c.getRoof().getSides();
        matList.putAll(matRoof.calcRoof(length, width, c.getRoof()));
    }
}