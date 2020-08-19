package online.javalearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calc {

    public static void main(String[] args) throws IOException {
        String expression;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Input:");
            expression = reader.readLine().trim();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-выода IOException");
            throw e;
        }

        try {
            Operator operator = new Operator(expression);
        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("Exception from Operator");
        }
    }
}
