package online.javalearn;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operator {
    private JmInteger a;
    private JmInteger b;
    private String operation;

    private static final String regex = "^([IVX]*|[0-9]+)\\s*([\\*\\+-\\/]?)\\s*([IVX]*|[0-9]+)$";
    //private static String regex = "^([IVX]*|[0-9]{1,})\\s*(.{1})\\s*([IVX]*|[0-9]{1,})$";

    Pattern pattern;

    public Operator(String userExpression) {
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        try {
            doCalc(userExpression);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void doCalc(String expression) throws NumberFormatException {

        Matcher matcher = pattern.matcher(expression);

        while(matcher.find()) {
            a = new JmInteger(matcher.group(1).toUpperCase());
            b = new JmInteger(matcher.group(3).toUpperCase());
            operation = matcher.group(2);
        }

        if (a == null) {
            throw new NumberFormatException("Неверное выражение. Только операции сложения, вычитания, умножения и деления.");
        }

        if ((a.isRoman() && !b.isRoman()) || (!a.isRoman() && b.isRoman())) {
            throw new NumberFormatException("Несовместимые типы");
        }

        try {
            Calculator calc = new Calculator(a.getValue(), b.getValue(), operation);
            JmInteger result = new JmInteger(calc.calculate());

            if (a.isRoman() && b.isRoman() && result.getValue() <= 0) {
                // System.out.println("Roman: " + result.toRoman());
                throw new ArithmeticException("Римское значение ноль или отрицательное!");
            }
            if (!a.isRoman() && !b.isRoman()) {
                System.out.println("Output: " + result.getValue());
            } else {
                System.out.println("Output: " + result.toRoman());
            }
        } catch (IndexOutOfBoundsException | InputMismatchException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
