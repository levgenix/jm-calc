package online.javalearn;

import java.util.InputMismatchException;

import static online.javalearn.Operation.*;

public class Calculator {

    private final int a;
    private final int b;
    private Operation operation;

    public Calculator(int a, int b, String op) throws IndexOutOfBoundsException, InputMismatchException, ArithmeticException {
        this.a = a;
        this.b = b;

        if (b == 0) {
            throw new ArithmeticException("Вероятно, что деление на ноль!");
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IndexOutOfBoundsException("Калькулятор оперирует только значениями в пределах значений [1...10]");
        }

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
            default:
                throw new InputMismatchException("Калькулятор выполняет только операции сложения, вычитания, умножения и деления!");
        }
    }

    public int calculate() throws ArithmeticException {
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
            }
        } catch (ArithmeticException e) {
            throw e;
        }

        return 0;
    }
}
