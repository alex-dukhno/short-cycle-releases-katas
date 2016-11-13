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
        assertThat(evaluator.evaluate("100"), is(100));
        assertThat(evaluator.evaluate("10"), is(10));
    }

    @Test
    public void evaluatesSum() throws Exception {
        assertThat(evaluator.evaluate("23+21"), is(44));
    }
}