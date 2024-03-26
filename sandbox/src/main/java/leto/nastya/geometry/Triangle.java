package leto.nastya.geometry;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double calculationAreaTriangle() {
        double p1 = (this.side1 + this.side2 + this.side3) / 2;
        double p2 = p1 * (p1 - this.side1) * (p1 - this.side2) * (p1 - this.side3);
        return Math.sqrt(p2);
    }

   static void printAreaTriangle(Triangle m) {
        String outputText = "Площадь треугольника со сторонами %.2f и %.2f и %.2f  = %.2f";
        String text = String.format(outputText, m.side1, m.side2, m.side3, m.calculationAreaTriangle());
        System.out.println(text);
    }
}
