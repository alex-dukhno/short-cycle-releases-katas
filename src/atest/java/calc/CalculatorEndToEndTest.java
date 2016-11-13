package calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorEndToEndTest {

    private Process calc;
    private BufferedWriter calcInput;
    private BufferedReader calcOutput;

    @Before
    public void setUp() throws Exception {
        calc = new ProcessBuilder("java", "-cp", "build/classes/main", "calc.Calculator").start();
        calcInput = new BufferedWriter(new OutputStreamWriter(calc.getOutputStream()));
        calcOutput = new BufferedReader(new InputStreamReader(calc.getInputStream()));
    }

    @After
    public void tearDown() throws Exception {
        calc.destroy();
    }

    @Test
    public void calculatorPrintsNumberThatUserEntered() throws Exception {
        calcInput.write("123");
        clickEnterButton();

        String result = calcOutput.readLine();
        assertThat(result, is("123"));
    }

    @Test
    public void calculatorPrintsSumOfTwoNumbers() throws Exception {
        calcInput.write("345+23");
        clickEnterButton();

        String result = calcOutput.readLine();
        assertThat(result, is("368"));
    }

    private void clickEnterButton() throws IOException {
        calcInput.write("\n");
        calcInput.flush();
    }
}
