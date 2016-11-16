package calc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvaluatorTest {

    private Evaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new Evaluator();
    }

    @Test
    public void evaluatesNumber() throws Exception {
        assertThat(evaluator.evaluate("100"), is(100.0));
        assertThat(evaluator.evaluate("10.0"), is(10.0));
    }

    @Test
    public void evaluatesSum() throws Exception {
        assertThat(evaluator.evaluate("23+21"), is(44.0));
    }

    @Test
    public void evaluatesSubtraction() throws Exception {
        assertThat(evaluator.evaluate("45-23"), is(22.0));
    }

    @Test
    public void evaluatesMultiplication() throws Exception {
        assertThat(evaluator.evaluate("23*4"), is(92.0));
    }

    @Test
    public void evaluatesDivision() throws Exception {
        assertThat(evaluator.evaluate("32/4"), is(8.0));
    }

    @Test
    public void evaluatesMultipleOperations() throws Exception {
        assertThat(evaluator.evaluate("32/8+10-6*2+23"), is(25.0));
    }

}
