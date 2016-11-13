package cacl;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CalculatorEndToEndTest {

    @Test
    public void calculatorPrintsNumberThatUserEntered() throws Exception {
        Process calc = new ProcessBuilder("java", "-cp", "build/classes/main", "calc.Calculator").start();

        BufferedWriter calcInput = new BufferedWriter(new OutputStreamWriter(calc.getOutputStream()));
        BufferedReader calcOutput = new BufferedReader(new InputStreamReader(calc.getInputStream()));
        calcInput.write("123\n");
        calcInput.flush();

        String result = calcOutput.readLine();
        assertThat("123", is(result));
    }
}
