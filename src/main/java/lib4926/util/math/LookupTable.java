package lib4926.util.math;

import java.util.List;
import java.util.function.Function;


public class LookupTable {

    private List<Double> valuesX;
    private List<Double> valuesY;
    private Function<Double, Double> defaultFunction;

    /**
     * Constructs a new lookup table with the specified x and y values and default function.
     * 
     * @param valuesX list of values for the independent variable
     * @param valuesY list of values for the dependent variable
     * @param default function to calculate y if x is outside lookup table's range
     */
    public LookupTable(List<Double> valuesX, List<Double> valuesY, Function<Double, Double> defaultFunction) {
        this.valuesX = valuesX;
        this.valuesY = valuesY;
        this.defaultFunction = defaultFunction;
    }


    /**
     * Takes in an x value and calculates its corresponding y value using linear interpolation with the two 
     * x values in the table that the specified x value is located between.
     * <p>
     * If the specified x value is outside the table's range, use the default function to calculate y.
     * 
     * @param x the x value to calculate y for
     * @return the calculated y value
     */
    public double getY(double x) {
        int fin = -1;

        if (x == valuesX.get(valuesX.size() - 1)) {
            return valuesY.get(valuesY.size() - 1);
        }

        for (int i = 0; i < valuesY.size(); i++) {
            if (valuesX.get(i) > x) {
                fin = i - 1;
                break;
            }
        }

        // If x is outside the lookup table's range, calculate y using the default function
        if (fin == -1 || (fin + 1 > valuesY.size() - 1)) {
            return defaultFunction.apply(x);
        }
        // If x is in the table's range, linearly interpolate y
        else {
            double lowerY = valuesY.get(fin);
            double upperY = valuesY.get(fin + 1);
            double lowerX = valuesX.get(fin);
            double upperX = valuesX.get(fin + 1);

            double interpolatedY = lowerY + (((x - lowerX) / (upperX - lowerX)) * (upperY - lowerY));
            return interpolatedY;
        }

    }

}
