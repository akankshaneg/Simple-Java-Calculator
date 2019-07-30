package tests;

import static java.lang.Math.pow;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import simplejavacalculator.Calculator;
import simplejavacalculator.Calculator.BiOperatorModes;

public class CalculatorTests {
    private static final double EPSILON = 0.00001;
    Calculator tester;

    @Before
    public void init() {
        tester = new Calculator();
    }

    @Test
    public void testAddFunction() {
        tester.num1 = getRandomNumber();
        tester.num2 = getRandomNumber();
        final Double expectedResult = tester.num1 + tester.num2;
        tester.mode = BiOperatorModes.add;
        Double result = tester.calculateBiImpl();

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testMinusFunction() {
        tester.num1 = getRandomNumber();
        tester.num2 = getRandomNumber();
        final Double expectedResult = tester.num1 - tester.num2;
        tester.mode = BiOperatorModes.minus;
        Double result = tester.calculateBiImpl();

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testMultiplyFunction() {
        tester.num1 = getRandomNumber();
        tester.num2 = getRandomNumber();
        final Double expectedResult = tester.num1 * tester.num2;
        tester.mode = BiOperatorModes.multiply;
        Double result = tester.calculateBiImpl();

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testDivideFunction() {
        tester.num1 = getRandomNumber();
        tester.num2 = getRandomNumber();
        final Double expectedResult = tester.num1 / tester.num2;
        tester.mode = BiOperatorModes.divide;
        Double result = tester.calculateBiImpl();

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testPowerFunction() {
        tester.num1 = getRandomNumber();
        tester.num2 = getRandomNumber();
        final Double expectedResult = pow(tester.num1, tester.num2);
        tester.mode = BiOperatorModes.xpowerofy;
        Double result = tester.calculateBiImpl();

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testSquareFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = tester.num1 * tester.num1;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.square, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testSquareRootFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.sqrt(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.squareRoot, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testOneDividedByFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = 1 / tester.num1;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testCosFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.cos(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.cos, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testSinFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.sin(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.sin, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testTanFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.tan(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.tan, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testLogFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.log10(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.log, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testRateFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = tester.num1 / 100;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.rate, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testAbsoluteFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = Math.abs(tester.num1);
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.abs, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testMemoryFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = tester.num1;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.mem, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testMemoryRecallFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = tester.num1;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.memRcl, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    @Test
    public void testMemoryClearFunction() {
        tester.num1 = getRandomNumber();
        final Double expectedResult = 0.0;
        Double result = tester.calculateMono(Calculator.MonoOperatorModes.memClr, tester.num1);

        assertEquals(expectedResult, result, EPSILON);
    }

    private Double getRandomNumber() {
        final Random random = new Random();
        final int minRange = -100;
        final int maxRange = 100;
        double value = minRange + (maxRange - minRange) * random.nextDouble();
        return value;
    }
}