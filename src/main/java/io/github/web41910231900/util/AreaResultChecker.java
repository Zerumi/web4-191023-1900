package io.github.web41910231900.util;

public class AreaResultChecker {
    public static boolean getResult(double x, double y, double r) {
        // check 1st square -- 1/4 circle
        if (x >= 0 && y >= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))
                return true;
        }
        // check 2nd square -- triangle
        if (x <= 0 && y >= 0) {
            if (2*x + r >= y)
                return true;
        }
        // check 4th square -- rectangle
        if (x >= 0 && y <= 0) {
            return x <= r / 2 && -y <= r;
        }
        // check 3rd square -- always false
        // also if all checks above fails...
        return false;
    }
}
