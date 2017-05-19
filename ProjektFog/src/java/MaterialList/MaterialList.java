package MaterialList;

import Carport.Carport;
import java.util.HashMap;
import java.util.Map;

public class MaterialList {

    private Map<String, Part> matList = new HashMap();
    private MatRoof matRoof;
    private MatBase matBase;
    private MatShed matShed;

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

        if (c.getShed().isHasShed()) {
            length = c.getBase().getDepth() + c.getShed().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        } else {
            length = c.getBase().getDepth() + c.getRoof().getFront() + c.getRoof().getBack();
        }

        int width = c.getBase().getWidth() + c.getRoof().getSides() + c.getRoof().getSides();

        matList.putAll(matRoof.calcRoof(length, width, c.getRoof()));

        matList.putAll(matBase.calcBase(length, width, c.getBase().getHeight(), c.getShed()));

        if (c.getShed() != null) {
            matList.putAll(matShed.calcShed(length, width));
        }

        return matList;
    }

}
