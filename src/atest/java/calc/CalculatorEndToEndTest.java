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
        enterMathExpression("123");
        clickEnterButton();

        assertThat(resultOfCalculation(), is("123"));
    }

    @Test
    public void calculatorPrintsSumOfTwoNumbers() throws Exception {
        enterMathExpression("345+23");
        clickEnterButton();

        assertThat(resultOfCalculation(), is("368"));
    }

    @Test
    public void calculatorPrintsEvaluatedSubtractionOfTwoNumbers() throws Exception {
        enterMathExpression("435-123");
        clickEnterButton();

        assertThat(resultOfCalculation(), is("312"));
    }

    @Test
    public void calculatorPrintsProductOfTwoNumbers() throws Exception {
        enterMathExpression("43*3");
        clickEnterButton();

        assertThat(resultOfCalculation(), is("129"));
    }

    @Test
    public void calculatorPrintsDivisionOfTwoNumbers() throws Exception {
        enterMathExpression("56/8");
        clickEnterButton();

        assertThat(resultOfCalculation(), is("7"));
    }

    private String resultOfCalculation() throws IOException {
        return calcOutput.readLine();
    }

    private void enterMathExpression(String expression) throws IOException {
        calcInput.write(expression);
    }

    private void clickEnterButton() throws IOException {
        calcInput.write("\n");
        calcInput.flush();
    }
}
