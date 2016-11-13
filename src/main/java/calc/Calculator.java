package calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Calculator {

    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Calculator calculator = new Calculator();
        out.write(String.valueOf(calculator.evaluate(in.readLine())));
        out.flush();
    }

    public int evaluate(String expression) {
        return Integer.parseInt(expression);
    }
}
