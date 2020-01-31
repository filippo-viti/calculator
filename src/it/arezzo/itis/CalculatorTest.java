package it.arezzo.itis;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @org.junit.Before
    public void setUp() {
        calculator = Calculator.getInstance();
        calculator.setOperand1(3);
        calculator.setOperand2(5);
        calculator.setCurrentOperator(Double::sum);
    }

    @org.junit.After
    public void tearDown() {
        calculator.clear();
        assertThat(calculator.getOperand1(), is(0.0));
        assertThat(calculator.getOperand2(), is(0.0));
        assertNull(calculator.getCurrentOperator());
    }

    @Test
    public void getInstance() {
        assertNotEquals(null, calculator);
        Calculator calculator1 = Calculator.getInstance();
        assertEquals(calculator1, calculator);
    }

    @org.junit.Test
    public void getOperand1() {
        assertThat(calculator.getOperand1(), is(3.0));
    }

    @org.junit.Test
    public void getOperand2() {
        assertThat(calculator.getOperand2(), is(5.0));
    }

    @org.junit.Test
    public void binaryOperation() {
        calculator.binaryOperation();
        assertThat(calculator.getOperand1(), is(8.0));
        assertThat(calculator.getOperand2(), is(5.0));
    }
}