package calc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    @Test
    public void createCalculator() {
        Calculator calculator = new Calculator();
    }

    @Test
    public void evaluatesNumber() throws Exception {
        Calculator calculator = new Calculator();
        assertThat(calculator.evaluate("100"), is(100));
        assertThat(calculator.evaluate("10"), is(10));
    }
}
