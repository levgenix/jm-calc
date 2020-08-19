package online.javalearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operator {
    private JmInteger a;
    private JmInteger b;
    private String operation;

    private final String regex = "^([IVX]*|[0-9]{1,})\\s*([\\*\\+-\\/]{1})\\s*([IVX]*|[0-9]{1,})$";

    public Operator(String userExpression) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        setupCalculator(pattern.matcher(userExpression));
    }

    private void setupCalculator(Matcher matcher) {
        while(matcher.find()) {
            /*System.out.println("found: " + matcher.group(1));
            System.out.println("found: " + matcher.group(2));
            System.out.println("found: " + matcher.group(3));*/
            a = new JmInteger(matcher.group(1).toUpperCase());
            b = new JmInteger(matcher.group(3).toUpperCase());
            operation = matcher.group(2);
        }

        // Проверка a и b на [1... 10]

        if (a != null) {
            Calculator calc = new Calculator(a.getValue(), b.getValue(), operation);
            JmInteger result = new JmInteger(calc.calculate());
            System.out.println("Calculator: " + result.getValue() + ": " + result.toRoman());
        }
    }
}
