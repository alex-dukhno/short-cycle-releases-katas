package calc;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class EvaluatorTest {

    private Evaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new Evaluator();
    }

    @Test
    public void evaluatesIntegerNumber() throws Exception {
        assertThat(evaluator.evaluate("100"), is(100.0));
    }

    @Test
    public void evaluatesFloatPointNumber() throws Exception {
        assertThat(evaluator.evaluate("10.0"), is(10.0));
    }

    @Test
    public void evaluatesIntegerNumberWithUnderscore() throws Exception {
        assertThat(evaluator.evaluate("1_000"), is(1000.0));
    }

    @Test
    public void evaluatesFloatNumberWithUnderscores() throws Exception {
        assertThat(evaluator.evaluate("10_000.000_1"), is(10000.0001));
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

    @Test
    public void evaluatesExpressionWithParenthesis() throws Exception {
        assertThat(evaluator.evaluate("24/(4+2)"), is(4.0));
    }

    public class ExceptionalNumberEvaluation {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void evaluate_20_comma_0_throwsException() throws Exception {
            evaluateExpression("20,0", "the number 20,0 in 1 position has an error");
        }

        @Test
        public void evaluate_10_comma_0_throwsException() throws Exception {
            evaluateExpression("10,0", "the number 10,0 in 1 position has an error");
        }

        private void evaluateExpression(String expression, String expectedMessage) {
            thrown.expect(Evaluator.NumberEvaluationException.class);
            thrown.expectMessage(is(expectedMessage));
            evaluator.evaluate(expression);
        }

        @Test
        public void expressionEvaluation_throwsException() throws Exception {
            evaluateExpression("1+20,0", "the number 20,0 in 2 position has an error");
        }
    }

}
