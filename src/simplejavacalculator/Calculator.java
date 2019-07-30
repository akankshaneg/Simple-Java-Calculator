package simplejavacalculator;

import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {

    public enum BiOperatorModes {

        normal, add, minus, multiply, divide, xpowerofy
    }

    public enum MonoOperatorModes {

        square, squareRoot, oneDividedBy, cos, sin, tan, log, rate, abs, mem, memRcl, memClr, bksp, decimal
    }

    public Double num1, num2, memNum;
    public BiOperatorModes mode = BiOperatorModes.normal;

    public Double calculateBiImpl() {
        if (mode == BiOperatorModes.normal) {
            return num2;
        }
        if (mode == BiOperatorModes.add) {
            return num1 + num2;
        }
        if (mode == BiOperatorModes.minus) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.multiply) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.divide) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.xpowerofy) {
            return pow(num1, num2);
        }

        // never reach
        throw new Error();
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return Double.NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return Double.NaN;
    }

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        if (newMode == MonoOperatorModes.square) {
            return num * num;
        }
        if (newMode == MonoOperatorModes.squareRoot) {
            return Math.sqrt(num);
        }
        if (newMode == MonoOperatorModes.oneDividedBy) {
            return 1 / num;
        }
        if (newMode == MonoOperatorModes.cos) {
            return Math.cos(num);
        }
        if (newMode == MonoOperatorModes.sin) {
            return Math.sin(num);
        }
        if (newMode == MonoOperatorModes.tan) {
            return Math.tan(num);
        }
        if (newMode == MonoOperatorModes.log) {
            return log10(num);
        }
        if (newMode == MonoOperatorModes.rate) {
            return num / 100;
        }
        if (newMode == MonoOperatorModes.abs) {
            return Math.abs(num);
        }

        // new code - memory function - store & return the value
        if (newMode == MonoOperatorModes.mem) {
            memNum = num;
            return memNum;
        }

        // new code - memory recall function - return the stored value
        if (newMode == MonoOperatorModes.memRcl) {
            if (memNum != null) {
                return memNum;
            } else {
                return num;
            }
        }

        // new code - memory clear function - clear the stored value
        if (newMode == MonoOperatorModes.memClr) {
            memNum = 0.0;
            return memNum;
        }

        // new code - Backspace functionality - return the current value
        if (newMode == MonoOperatorModes.bksp) {
            return num;
        }

        // never reach
        throw new Error();
    }

}
