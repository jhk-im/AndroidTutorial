package com.jroomstudio.unittestuitest;

import com.jroomstudio.unittestuitest.sample.Calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void addTest() {
        int result = calculator.add(15,10);
        assertEquals(25, result);
    }

    @Test
    public void subtractTest() {
        int result = calculator.subtract(15,10);
        assertEquals(5, result);
    }

}
