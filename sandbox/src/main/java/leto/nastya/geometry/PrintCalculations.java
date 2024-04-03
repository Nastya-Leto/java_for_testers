package leto.nastya.geometry;

public class PrintCalculations {
    static void printAreaTriangle(Triangle m) {
        String outputText = "Площадь треугольника со сторонами %.2f и %.2f и %.2f  = %.2f";
        String text = String.format(outputText, m.side1, m.side2, m.side3, m.calculationAreaTriangle());
        System.out.println(text);
    }
    static void pintPerimeterTriangle(Triangle n) {
        String outputText = "Периметр треугольника со сторонами %.2f и %.2f и %.2f  = %.2f";
        String text = String.format(outputText, n.side1, n.side2, n.side3, n.calculationPerimeter());
        System.out.println(text);
    }
}
