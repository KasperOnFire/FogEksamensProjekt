package Backend;

public class GarageCalc {

    public String priceCalculatorToString(int length, int width) {
        return String.format("%.2f", (double) priceCalculator(length, width));
    }

    
    public double priceCalculator(int length, int width) {
        if (length <= 100 || width <= 100) {
            throw new IllegalArgumentException();
        }
        return priceRoof(length, width) + priceLegs(length, width);
    }

    private double priceRoof(int length, int width) {
        double res = 0;
        double kvMeter = length / 100 * width / 100;
        double price = 200;
        res = kvMeter * price;
        return res;
    }

    private double priceLegs(int length, int width) {
        double res = 0;
        int legs = (length <= 400 ? 2 : (int) Math.ceil((double) length / 200));

        double price = 100;

        res = 2 * legs * price;
        return res;
    }
}
