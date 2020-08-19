package online.javalearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operator {
    private JmInteger a;
    private JmInteger b;
    private String operation;

    public Operator(String userExpression) {
        String regex = "^([IVX]*|[0-9]{1,})\\s*([\\*\\+-\\/]{1})\\s*([IVX]*|[0-9]{1,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        setupCalculator(pattern.matcher(userExpression));
    }

    private void setupCalculator(Matcher matcher) { // TODO продумать наименование метода
        while(matcher.find()) {
            /*System.out.println("found: " + matcher.group(1));
            System.out.println("found: " + matcher.group(2));
            System.out.println("found: " + matcher.group(3));*/
            a = new JmInteger(matcher.group(1).toUpperCase());
            b = new JmInteger(matcher.group(3).toUpperCase());
            operation = matcher.group(2);
        }

        // TODO Проверка a и b на [1... 10]
        if (a.getValue() < 1 || a.getValue() > 10 || b.getValue() < 1 || b.getValue() > 10) {
            System.out.println("Калькулятор оперирует только значениями в пределах значений [1...10]");
            // TODO выбросить исключение вроде OutOfRange
        }

        if (a != null) {
            Calculator calc = new Calculator(a.getValue(), b.getValue(), operation);
            JmInteger result = new JmInteger(calc.calculate());

            if (a.isRoman() && b.isRoman()) {
                // TODO Проверка на отрицательное или нуль при римском выводе
                System.out.println("Roman: " + result.toRoman());
            } else if (!a.isRoman() && !b.isRoman()) {
                System.out.println("Arabic: " + result.getValue());
            } else {
                System.out.println("Calculator: " + result.getValue() + ": " + result.toRoman());
                // TODO Выбрасывать исключение
            }
        } // TODO else
    }
}
