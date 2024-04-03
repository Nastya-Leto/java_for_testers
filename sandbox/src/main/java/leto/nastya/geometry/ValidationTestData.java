package leto.nastya.geometry;

public class ValidationTestData {

    public static void checkSideTriangleNotNegative(double side1, double side2, double side3){
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной");
        }
    }

    public static void checkSideLengthsTriangle(double side1, double side2, double side3){
        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new IllegalArgumentException("Cумма двух сторон треугольника не может быть меньше третьей стороны");
        }
    }
}
