package leto.nastya.geometry;

import static leto.nastya.geometry.PrintCalculations.pintPerimeterTriangle;
import static leto.nastya.geometry.PrintCalculations.printAreaTriangle;


public class Geometry {
    public static void main(String[] args) {
        printAreaTriangle(new CalculationTriangle(4, 5, 6));
        pintPerimeterTriangle(new CalculationTriangle(4,7,2));
    }
}
