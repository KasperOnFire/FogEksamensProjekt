package Backend;

/**
 *
 * This class calculates estimated prices of a carport of given dimensions.
 *
 */
public class GarageCalc {

    /**
     *
     * Calculates the total price of carportwith a gabled roof
     *
     * @param length the length of the carport.
     * @param width the width of the carport.
     * @return the estimated price of the carport
     */
    public String priceCalculatorToString(int length, int width) {
        return String.format("%.2f", (double) priceCalculator(length, width));
    }

    /**
     *
     * Calculates the total price of carportwith a flat roof
     *
     * @param length the length of the carport.
     * @param width the width of the carport.
     * @return the estimated price of the carport
     */
    public double priceCalculator(int length, int width) {
        if (length <= 100 || width <= 100) {
            throw new IllegalArgumentException();
        }
        return priceRoof(length, width) + priceLegs(length, width);
    }

    //Method calculates the est. price of a flat carport-roof
    private double priceRoof(int length, int width) {
        double res = 0;
        double kvMeter = length / 100 * width / 100;
        double price = 200;
        res = kvMeter * price;
        return res;
    }

    //Method defines how many legs a given carport needs
    private double priceLegs(int length, int width) {
        double res = 0;
        int legs = (length <= 400 ? 2 : (int) Math.ceil((double) length / 200));

        double price = 100;

        res = 2 * legs * price;
        return res;
    }
}
