package online.javalearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calc {

    public static void main(String[] args) {
        String expression = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            expression = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Operator operator = new Operator(expression);
        } catch (Exception e) {
            System.out.println("Exception from Operator");
        }
    }
}
