package MaterialList;

import Carport.Carport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaterialList {

    private Map<String, Part> matList = new HashMap();
    private MatRoof matRoof = new MatRoof();
    private MatBase matBase = new MatBase();
    private MatShed matShed = new MatShed();

    public int calcPrice(Carport c) throws Exception {
        calcMaterialList(c);
        return totalPriceRounded();
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
        //matList = new HashMap();
        int length;
        ArrayList<Material> materials = new ArrayList();
        DatabaseBack DBB = new DatabaseBack();
        materials = DBB.getAll();
        if (c.getShed().isHasShed()) {
            length = c.getBase().getDepth() + c.getShed().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        } else {
            length = c.getBase().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        }

        int width = c.getBase().getWidth() + c.getRoof().getSides() + c.getRoof().getSides();

        matList.putAll(matRoof.calcRoof(length, width, c.getRoof(), materials));

        matList.putAll(matBase.calcBase(length, width, c.getBase().getHeight(), c.getShed(), materials));

        if (c.getShed() != null) {
            matList.putAll(matShed.calcShed(length, width, materials));

            return matList;
        }
        return matList;
    }
}
