package leto.nastya.geometry;

import java.util.Arrays;

public class TriangleArray {

    public double side1;
    public double side2;
    public double side3;

    public TriangleArray(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriangleArray triangleArray = (TriangleArray) o;
        double[] sideThis = {side1,side2,side3};
        double[] sideOther = {triangleArray.side1,triangleArray.side2,triangleArray.side3};
        Arrays.sort(sideThis);
        Arrays.sort(sideOther);
        return Arrays.equals(sideThis,sideOther);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
