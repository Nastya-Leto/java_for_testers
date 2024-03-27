package leto.nastya.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Float.NaN;

public class CalculationTriangleTests {

    @Test
    void calculateAreaFirstPositiveTest() {
        var s = new CalculationTriangle(5.0, 5.0, 5.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(10.83, result, 0.01);
    }

    @Test
    void calculateAreaSecondPositiveTest() {
        var s = new CalculationTriangle(2.5, 3.0, 4.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(3.75, result, 0.01);
    }

    @Test
    void calculateAreaWithZeroTest() {
        var s = new CalculationTriangle(0, 8.0, 9.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(NaN, result, "Некорректные входящие данные");
    }

    @Test
    void calculatePerimeterFirstPositiveTest() {
        var s = new CalculationTriangle(4, 7.5, 3.0);
        double result = s.calculationPerimeter();
        Assertions.assertEquals(14.5, result);
    }
    @Test
    void calculatePerimeterSecondPositiveTest() {
        var s = new CalculationTriangle(10.0, 8.0, 9.0);
        double result = s.calculationPerimeter();
        Assertions.assertEquals(27.0, result);
    }
}
