package calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Calculator {

    private final BufferedReader in;
    private final BufferedWriter out;
    private final NumberFormat outputFormatter;

    public Calculator(InputStream inputStream, OutputStream outputStream) {
        in = new BufferedReader(new InputStreamReader(inputStream));
        out = new BufferedWriter(new OutputStreamWriter(outputStream));
        outputFormatter = new DecimalFormat("#.################");
    }

    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator(System.in, System.out);
        Evaluator evaluator = new Evaluator();
        calculator.printsResult(evaluator.evaluate(calculator.readExpression()));
    }

    public String readExpression() throws IOException {
        return in.readLine();
    }

    public void printsResult(double evaluatedResult) throws IOException {
        out.write(outputFormatter.format(evaluatedResult) + "\n");
        out.flush();
    }
}
