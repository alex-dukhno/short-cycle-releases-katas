package calc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Evaluator {

    private final Set<Character> supportedOperation;
    private final Map<Character, BiFunction<Double, Expression, Double>> operationFunction;

    public Evaluator() {
        supportedOperation = new HashSet<>();
        supportedOperation.add('+');
        supportedOperation.add('-');
        supportedOperation.add('*');
        supportedOperation.add('/');

        operationFunction = new HashMap<>();

        operationFunction.put('*', (acc, exp) -> acc * parseArgument(exp.incrementIndex()));
        operationFunction.put('/', (acc, exp) -> acc / parseArgument(exp.incrementIndex()));

        operationFunction.put('+', (acc, exp) -> acc + parseTerm(exp.incrementIndex()));
        operationFunction.put('-', (acc, exp) -> acc - parseTerm(exp.incrementIndex()));
    }

    public double evaluate(String expression) {
        return parseExpression(new Expression(expression));
    }

    private double parseExpression(Expression exp) {
        double result = parseTerm(exp);
        while (exp.hasMoreArguments()) {
            result = operationFunction.get(exp.getCurrentChar()).apply(result, exp);
        }
        return result;
    }

    private double parseTerm(Expression exp) {
        double result = parseArgument(exp);
        while (exp.hasMoreArguments() && (exp.getCurrentChar() == '*' || exp.getCurrentChar() == '/')) {
            result = operationFunction.get(exp.getCurrentChar()).apply(result, exp);
        }
        return result;
    }

    private double parseArgument(Expression exp) {
        int start = exp.index;
        while (exp.hasMoreArguments() && !supportedOperation.contains(exp.getCurrentChar())) {
            exp.index += 1;
        }
        return Double.parseDouble(exp.src.substring(start, exp.index));
    };

    private static class Expression {
        private final String src;
        private int index;

        Expression(String src) {
            this.src = src;
            index = 0;
        }

        boolean hasMoreArguments() {
            return index < src.length();
        }

        char getCurrentChar() {
            return src.charAt(index);
        }

        Expression incrementIndex() {
            index += 1;
            return this;
        }
    }
}
