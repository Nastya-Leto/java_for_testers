package leto.nastya.geometry;

public class CalculationTriangle {

    public double side1;
    public double side2;
    public double side3;

    public CalculationTriangle(double side1, double side2, double side3) {

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной");
        }

        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new IllegalArgumentException("Cумма двух сторон треугольника не может быть меньше третьей стороны");
        }
    }

    public double calculationAreaTriangle() {
        double p1 = (side1 + side2 + side3) / 2;
        double p2 = p1 * (p1 - side1) * (p1 - side2) * (p1 - side3);
        return Math.sqrt(p2);
    }

    public double calculationPerimeter() {
        return side1 + side2 + side3;
    }
}
