package leto.nastya.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void cannotCreateWithTriangleNegativeSideA() {
        try {
            var s = new CalculationTriangle(-10.0, 8.0, 9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный");
        }
    }

    @Test
    void cannotCreateWithTriangleNegativeSideB() {
        try {
            var s = new CalculationTriangle(10.0, -8.0, 9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный");
        }
    }

    @Test
    void cannotCreateWithTriangleNegativeSideC() {
        try {
            var s = new CalculationTriangle(10.0, 8.0, -9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleIncorrectSideA() {
        try {
            var s = new CalculationTriangle(0, 8.0, 9.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }
    @Test
    void cannotCreateWithTriangleIncorrectSideB() {
        try {
            var s = new CalculationTriangle(12, 1.0, 9.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }
    @Test
    void cannotCreateWithTriangleIncorrectSideC() {
        try {
            var s = new CalculationTriangle(5, 7.0, 1.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }
}
