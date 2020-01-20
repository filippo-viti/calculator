package it.arezzo.itis;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class Calculator {
    private static Calculator instance;
    private double operand1;
    private double operand2;
    private DoubleBinaryOperator currentOperator;

    private Calculator() {
        clear();
    }

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public DoubleBinaryOperator getCurrentOperator() {
        return currentOperator;
    }

    public void setCurrentOperator(DoubleBinaryOperator currentOperator) {
        this.currentOperator = currentOperator;
    }

    public void unaryOperation(DoubleUnaryOperator operation) {
        if (operand1 == 0 && operand2 == 0) {
            operand1 = 0;
        }
        operand1 = operand1 == 0 ? operation.applyAsDouble(operand2) : operation.applyAsDouble(operand1);
    }

    public void binaryOperation() {
        operand1 = currentOperator.applyAsDouble(operand1, operand2);
    }

    public void clear() {
        operand1 = 0;
        operand2 = 0;
        currentOperator = null;
    }


}
