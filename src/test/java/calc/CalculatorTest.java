package calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    private PipedInputStream in;
    private PipedOutputStream out;
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        in = new PipedInputStream();
        out = new PipedOutputStream(in);
        calculator = new Calculator(in, out);
    }

    @After
    public void closeStreams() throws Exception {
        in.close();
        out.close();
    }

    @Test
    public void calculatorReadsFromInput() throws Exception {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write("expression");
        writer.write("\n");
        writer.flush();

        assertThat(calculator.readExpression(), is("expression"));
    }

    @Test
    public void calculatorWritesToOutput() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        calculator.printsResult("evaluated-result");

        assertThat(reader.readLine(), is("evaluated-result"));
    }
}
