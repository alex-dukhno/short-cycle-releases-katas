package calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Calculator {

    private final BufferedReader in;
    private final BufferedWriter out;

    public Calculator(InputStream inputStream, OutputStream outputStream) {
        in = new BufferedReader(new InputStreamReader(inputStream));
        out = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator(System.in, System.out);
        Evaluator evaluator = new Evaluator();
        int result = evaluator.evaluate(calculator.readExpression());
        calculator.printsResult(String.valueOf(result));
    }

    public String readExpression() throws IOException {
        return in.readLine();
    }

    public void printsResult(String evaluatedResult) throws IOException {
        out.write(evaluatedResult + "\n");
        out.flush();
    }
}
