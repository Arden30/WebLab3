package utils;

import java.io.Serializable;

public class AreaChecker implements Serializable {

    private AreaChecker() {
    }

    public static boolean checkHit(double x, double y, double r) {
        boolean rectangle = false;
        boolean triangle = false;
        boolean circle = false;

        if (x >= 0 && x <= r / 2 && y >= 0 && y <= r) {
            rectangle = true;
        }
        if (x >= -r && x <= 0 && y >= 0 && y <= x + r ) {
            triangle = true;
        }
        if (x >= -r && x <= 0 && y >= -r && y <= 0 && x * x + y * y <= r * r) {
            circle = true;
        }

        return rectangle || triangle || circle;
    }
}
