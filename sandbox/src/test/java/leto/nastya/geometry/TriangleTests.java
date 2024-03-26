package leto.nastya.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Float.NaN;

public class TriangleTests {

    @Test
    void calculateAreaFirstPositiveTest() {
        var s = new Triangle(5.0, 5.0, 5.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(10.83, result, 0.01);
    }

    @Test
    void calculateAreaSecondPositiveTest() {
        var s = new Triangle(2.5, 3.0, 4.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(3.75, result, 0.01);
    }

    @Test
    void calculateAreaWithZeroTest() {
        var s = new Triangle(0, 8.0, 9.0);
        double result = s.calculationAreaTriangle();
        Assertions.assertEquals(NaN, result, "Некорректные входящие данные");
    }

}
