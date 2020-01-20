package it.arezzo.itis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.text.DecimalFormat;
import java.util.function.DoubleBinaryOperator;

public class Controller {

    private DecimalFormat df = new DecimalFormat("#.######");
    private Calculator calculator;

    @FXML
    private Button zeroButton; //dichiarazione pulsanti dei numeri

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button sqrtButton; //pulsante radice

    @FXML
    private Button clearButton;

    @FXML
    private Button divButton;

    @FXML
    private Button mulButton;

    @FXML
    private Button subButton;

    @FXML
    private Button addButton;

    @FXML
    private Button equalsButton;

    @FXML
    private Button commaButton;

    @FXML
    private Button signButton; //cambia segno

    @FXML
    private Label numberLabel;

    @FXML
    private Button backspaceButton;

    @FXML
    void initialize() {
        zeroButton.setUserData(0);
        oneButton.setUserData(1);
        twoButton.setUserData(2);
        threeButton.setUserData(3);
        fourButton.setUserData(4);
        fiveButton.setUserData(5);
        sixButton.setUserData(6);
        sevenButton.setUserData(7);
        eightButton.setUserData(8);
        nineButton.setUserData(9);
        addButton.setUserData('+');
        subButton.setUserData('-');
        mulButton.setUserData('x');
        divButton.setUserData('/');
        commaButton.setUserData(',');
        numberLabel.setUserData(0);
        calculator = Calculator.getInstance();
    }

    @FXML
    void numberPressed(ActionEvent event) {
        Button source = (Button) event.getSource();
        addNumber((int) source.getUserData());
    }

    private void addNumber(int number) {
        if (numberLabel.getText().equals("0")) {
            numberLabel.setText("");
        }
        numberLabel.setText(numberLabel.getText() + number);
    }

    @FXML
    void comma(ActionEvent event) {
        addComma();
    }

    private void addComma() {
        if (!numberLabel.getText().contains(",")) {
            numberLabel.setText(numberLabel.getText() + ',');
        }
    }

    @FXML
    void operation(ActionEvent event) {
        Button source = (Button) event.getSource();
        changeOperator((char) source.getUserData());
    }

    private void changeOperator(char operator) {
        calculator.setOperand1(Double.parseDouble(numberLabel.getText().replace(",", ".")));
        if (calculator.getOperand1() != 0) {
            DoubleBinaryOperator binaryOperator;
            switch (operator) {
                case '+':
                    binaryOperator = Double::sum;
                    break;
                case '-':
                    binaryOperator = (x, y) -> x - y;
                    break;
                case 'x':
                    binaryOperator = (x, y) -> x * y;
                    break;
                case '/':
                    binaryOperator = (x, y) -> {
                        if (y == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        return x / y;
                    };
                    break;
                default:
                    binaryOperator = null;
            }
            calculator.setCurrentOperator(binaryOperator);
            calculator.setOperand2(0);
            numberLabel.setText("0");
        }
    }

    @FXML
    void calc(ActionEvent event) {
        try {
            calculator.setOperand2(Double.parseDouble(numberLabel.getText().replace(",", ".")));
            calculator.binaryOperation();
            numberLabel.setText(df.format(calculator.getOperand1()));
        } catch (ArithmeticException e) {
            e.printStackTrace();
            numberLabel.setText("Math error");
            calculator.clear();
        }
    }

    @FXML
    void squareRoot(ActionEvent event) {
        double value = Double.parseDouble(numberLabel.getText().replace(",", "."));
        if (value >= 0) {
            value = Math.sqrt(value);
            numberLabel.setText(df.format(value));
        } else {
            numberLabel.setText("Errore");
        }
    }

    @FXML
    void changeSign(ActionEvent event) {
        if (numberLabel.getText().equals("0")) {
            numberLabel.setText(numberLabel.getText().replace("0", "-"));
        } else {
            numberLabel.setText(df.format(-Double.parseDouble(numberLabel.getText().replace(",", "."))));
        }
    }

    @FXML
    void delete(ActionEvent event) {
        clear();
    }

    private void clear() {
        calculator.clear();
        numberLabel.setText("0");
    }

    @FXML
    void backspace(ActionEvent event) {
        String labelText = numberLabel.getText();
        if (!labelText.equals("0") && !labelText.equals("")) {
            numberLabel.setText(labelText.substring(0, labelText.length() - 1));
            if (numberLabel.getText().equals("")) {
                numberLabel.setText("0");
            }
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        //TODO implement numpad input
    }
}