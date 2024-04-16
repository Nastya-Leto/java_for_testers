package leto.nastya.geometry;

import java.util.Objects;

public class Triangle {

    public double side1;
    public double side2;
    public double side3;

    public Triangle(double side1, double side2, double side3) {

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        ValidationTestData.checkSideTriangleNotNegative(side1, side2, side3);
        ValidationTestData.checkSideLengthsTriangle(side1, side2, side3);
    }

    public double calculationAreaTriangle() {
        double p1 = calculationPerimeter() / 2;
        double p2 = p1 * (p1 - side1) * (p1 - side2) * (p1 - side3);
        return Math.sqrt(p2);
    }

    public double calculationPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(side1, triangle.side1) == 0 && Double.compare(side2, triangle.side2) == 0 && Double.compare(side3, triangle.side3) == 0
                || Double.compare(side1, triangle.side2) == 0 && Double.compare(side2, triangle.side3) == 0 && Double.compare(side3, triangle.side1) == 0
                || Double.compare(side1, triangle.side2) == 0 && Double.compare(side2, triangle.side1) == 0 && Double.compare(side3, triangle.side3) == 0
                || Double.compare(side1, triangle.side3) == 0 && Double.compare(side2, triangle.side1) == 0 && Double.compare(side3, triangle.side2) == 0
                || Double.compare(side1, triangle.side3) == 0 && Double.compare(side2, triangle.side2) == 0 && Double.compare(side3, triangle.side1) == 0
                || Double.compare(side1, triangle.side1) == 0 && Double.compare(side2, triangle.side3) == 0 && Double.compare(side3, triangle.side2) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
