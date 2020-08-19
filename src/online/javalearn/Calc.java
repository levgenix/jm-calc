package online.javalearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {

    public static void main(String[] args) {
        String expression = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            expression = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String regex =  "^([IVX]*|[0-9]{1,})\\s*([\\*\\+-\\/]{1})\\s*([IVX]*|[0-9]{1,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);

        JmInteger a = null;
        JmInteger b = null;
        String operation = null;

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
