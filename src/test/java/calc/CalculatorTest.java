package calc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void createCalculator() {
    }

    @Test
    public void evaluatesNumber() throws Exception {
        assertThat(calculator.evaluate("100"), is(100));
        assertThat(calculator.evaluate("10"), is(10));
    }

    @Test
    public void evaluatesSum() throws Exception {
        assertThat(calculator.evaluate("23+21"), is(44));
    }
}
