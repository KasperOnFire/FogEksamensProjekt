package MaterialList;

import Carport.Roof;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatRoof {

    private static double m2;
    private Map<String, Part> roofMap = new HashMap<>();
    private Part p;
    private static int mountingBands = 2;

    private int findPrice(ArrayList<Material> a, String s) {
        for (Material m : a) {
            if (m.getName().equals(s)) {
                return m.getPrice();
            }
        }
        return 2;
    }

    public Map<String, Part> calcRoof(int length, int depth, Roof r, ArrayList a) throws Exception {
        m2 = (double) (length / 100) * (double) (depth / 100);
        System.out.println(r.isGable());
        if (r.isGable()) {
            p = new Part(gabledRoof(length, depth), findPrice(a, "Tagpap"));
            roofMap.put("Tagpap", p);

            p = new Part(gabledBoards(length, depth), findPrice(a, "Spærtræ"));
            roofMap.put("Tagspær", p);

            p = new Part(roofBoardScrews(roofBoards(length)), findPrice(a, "Spærskrue"));
            roofMap.put("Spærskrue", p);

            p = new Part(1, findPrice(a, "Danspær"));
            roofMap.put("Danspær", p);
        } else {
            p = new Part(flatRoof(length, depth), findPrice(a, "Plastmo Trapez"));
            roofMap.put("Plastmo Trapez", p);

            p = new Part(roofScrews(flatRoof(length, depth)), findPrice(a, "Plastmo Tagskrue"));
            roofMap.put("Plastmo Tagskrue", p);

            p = new Part(2, findPrice(a, "Hulbånd"));
            roofMap.put("Hulbånd", p);

            p = new Part(roofBoards(length), findPrice(a, "Spærtræ"));
            roofMap.put("Tagspær", p);

            p = new Part(roofBoardScrews(roofBoards(length)), findPrice(a, "Spærskrue"));
            roofMap.put("Spærskrue", p);

            p = new Part(roofBoardBrackets(roofBoards(length)), findPrice(a, "Tagspærbeslag"));
            roofMap.put("Spærbeslag", p);

            p = new Part(mountingBandScrews(roofBoards(length), mountingBands), findPrice(a, "Spærskrue"));
            roofMap.put("Hulbåndsskrue", p);

            p = new Part(2, findPrice(a, "Sternbræt"), depth);
            roofMap.put("Understernbræt, side", p);

            p = new Part(2, findPrice(a, "Sternbræt"), depth);
            roofMap.put("Oversternbræt, side", p);

            p = new Part(1, findPrice(a, "Sternbræt"), length);
            roofMap.put("Understernbræt, forende", p);

            p = new Part(1, findPrice(a, "Sternbræt"), length);
            roofMap.put("Oversternbræt, forende", p);
        }
        return roofMap;
    }

    private int flatRoof(int length, int width) {
        int fr = (int) Math.ceil(m2) + 1;
        return fr;
    }

    private int flatRoof() {
        return (int) Math.ceil(m2);
    }

    private int roofScrews(double m2) {
        int rs = (int) m2 * 12;
        return rs;
    }

    private int roofBoards(int length) {
        int rb = (length / 60) + 1;
        return rb;
    }

    private int roofBoardScrews(int roofBoards) {
        int rbs = roofBoards * 18;
        return rbs;
    }

    private int roofBoardBrackets(int roofBoards) {
        int rbb = roofBoards * 2;
        return rbb;
    }

    private int mountingBandScrews(int roofBoards, int mountingBands) {
        return roofBoards * mountingBands;
    }

    private int gabledRoof(int length, int depth) {
        double a = (double) length / 2;
        double a2 = a * a;
        double b = Math.log(depth);
        double b2 = b * b;
        double size = Math.sqrt(a2 + b2) * length;
        return (int) size;
    }

    private int gabledBoards(int length, int depth) {
        double a = (double) length / 2;
        double a2 = a * a;
        double b = Math.log(depth);
        double b2 = b * b;
        double amount = Math.ceil(Math.sqrt(a2 + b2) / 35);
        return (int) amount;
    }
}
