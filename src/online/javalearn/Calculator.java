package online.javalearn;

import java.util.InputMismatchException;

import static online.javalearn.Operation.*;

public class Calculator {

    private final int a;
    private final int b;
    private Operation operation;

    public Calculator(int a, int b, String op) {
        this.a = a;
        this.b = b;

        switch (op) {
            case "+":
                operation = ADD;
                break;
            case "-":
                operation = SUBTRACT;
                break;
            case "*":
                operation = MULTIPLY;
                break;
            case "/":
                operation = DIVIDE;
                break;
        }
    }

    public int calculate() throws InputMismatchException, ArithmeticException {
        try {
            switch (operation) {
                case ADD:
                    return a + b;
                case SUBTRACT:
                    return a - b;
                case MULTIPLY:
                    return a * b;
                case DIVIDE:
                    return a / b;
                default:
                    // TODO System.out.println("sdsdasdsada");
                    throw new InputMismatchException("Калькулятор выполняет только операции сложения, вычитания, умножения и деления!");
            }
        } catch (ArithmeticException ex) {
            System.out.println("ArithmeticException");
        }
        return 0;
    }
}
