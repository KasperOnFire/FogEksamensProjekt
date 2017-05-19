package MaterialList;

import Carport.Carport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaterialList {

    private int mountingBands = 2;
    private Map<String, Part> matList = new HashMap<>();
    private Part p;
    private MatRoof matRoof;
    private MatBase matBase;
    private MatShed matShed;
    private double m2;
    private Map<String, Part> roofMap = new HashMap<>();
    private Map<String, Part> baseMap = new HashMap<>();
    private Map<String, Part> shedMap = new HashMap<>();
    
    
    private double getPrice(String name) throws Exception
    {
        DatabaseBack DBB = new DatabaseBack();
        return DBB.getDouble(name);
    }
    
    public int totalPriceRounded() {
        double totalPrice = 0;

        for (Map.Entry<String, Part> part : matList.entrySet()) {
            totalPrice += part.getValue().getAmount() * part.getValue().getPrice();
        }

        double totalAdjustedPrice = Math.ceil(totalPrice / 1000) * 1000;

        return (int) totalAdjustedPrice;
    }
    
    public Map<String, Part> calcMaterialList(Carport c) throws Exception {
        int length;
        ArrayList<Material> materials = new ArrayList();
        DatabaseBack DBB = new DatabaseBack();
        materials = DBB.getAll();
        if (c.getShed().isHasShed()) {
            length = c.getBase().getDepth() + c.getShed().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        } else {
            length = c.getBase().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        }
        
        int width = c.getBase().getWidth()+c.getRoof().getSides()+c.getRoof().getSides();
        
        matList.putAll(matRoof.calcRoof(length, width, c.getRoof(), materials));
        
        matList.putAll(matBase.calcBase(length, width, c.getBase().getHeight(), c.getShed(), materials));
        
        if(c.getShed()!=null){
            matList.putAll(matShed.calcShed(length, width, materials));
        }

        return matList;
    }
    
    
}

