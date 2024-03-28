package leto.nastya.geometry;

public class CalculationTriangle {

    public double side1;
    public double side2;
    public double side3;

    public CalculationTriangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double calculationAreaTriangle() {
        double p1 = calculationPerimeter() / 2;
        double p2 = p1 * (p1 - side1) * (p1 - side2) * (p1 - side3);
        return Math.sqrt(p2);
    }

    public double calculationPerimeter() {
        return side1 + side2 + side3;
    }


}
