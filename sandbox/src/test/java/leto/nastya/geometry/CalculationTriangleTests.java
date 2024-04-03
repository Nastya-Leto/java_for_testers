package leto.nastya.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculationTriangleTests {

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
    void calculatePerimeterFirstPositiveTest() {
        var s = new Triangle(6.5, 4, 9);
        double result = s.calculationPerimeter();
        Assertions.assertEquals(19.5, result);
    }

    @Test
    void calculatePerimeterSecondPositiveTest() {
        var s = new Triangle(10.0, 8.0, 9.0);
        double result = s.calculationPerimeter();
        Assertions.assertEquals(27.0, result);
    }

    @Test
    void cannotCreateWithTriangleNegativeSideA() {
        try {
            var s = new Triangle(-10.0, 8.0, 9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleNegativeSideB() {
        try {
            var s = new Triangle(10.0, -8.0, 9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleNegativeSideC() {
        try {
            var s = new Triangle(10.0, 8.0, -9.0);
            Assertions.fail("Стороны не отрицательные");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleIncorrectSideA() {
        try {
            var s = new Triangle(0, 8.0, 9.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleIncorrectSideB() {
        try {
            var s = new Triangle(12, 1.0, 9.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void cannotCreateWithTriangleIncorrectSideC() {
        try {
            var s = new Triangle(5, 7.0, 1.0);
            Assertions.fail("Условие правильного треугольника выполняется");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест успешный, поймали исключение");
        }
    }

    @Test
    void checkEqualsSideTriangleTest() {
        var v1 = new Triangle(4,5,6);
        var v2 = new Triangle(4,5,6);
        Assertions.assertTrue(v1.equals(v2));
    }

    @Test
    void checkEqualsSideTriangleTest2() {
        var v1 = new Triangle(4,5,6);
        var v2 = new Triangle(5,4,6);
        Assertions.assertTrue(v1.equals(v2));
    }

    @Test
    void checkEqualsSideTriangleTest3() {
        var v1 = new Triangle(4,5,6);
        var v2 = new Triangle(6,5,4);
        Assertions.assertTrue(v1.equals(v2));
    }

    @Test
    void checkEqualsSideTriangleTest4() {
        var v1 = new Triangle(4,5,6);
        var v2 = new Triangle(6,5,4);
        Assertions.assertEquals(v1,v2);
    }

    @Test
    void checkEqualsSideTriangleArrayTest1() {
        var v1 = new TriangleArray(4,5,6);
        var v2 = new TriangleArray(6,5,4);
        Assertions.assertEquals(v1,v2);

    }
}
