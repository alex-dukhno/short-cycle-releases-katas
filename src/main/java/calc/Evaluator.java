package calc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Evaluator {

    private final Map<Character, BiFunction<Double, Expression, Double>> operationFunction;
    private final Set<Character> operators;
    private int position = 0;

    public Evaluator() {
        operators = new HashSet<>();

        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');

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
        while (exp.hasMoreArguments() && exp.getCurrentChar() != ')') {
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
        if (exp.getCurrentChar() == '(') {
            double result = parseExpression(exp.incrementIndex());
            exp.incrementIndex();
            return result;
        } else {
            position += 1;
            int start = exp.index;
            if (position == 1 && exp.getCurrentChar() == '-') {
                exp.index += 1;
            }
            while (exp.hasMoreArguments() && !operators.contains(exp.getCurrentChar()) && exp.getCurrentChar() != ')') {
                exp.index += 1;
            }
            String number = exp.src.substring(start, exp.index);
            try {
                return Double.parseDouble(number.replaceAll("_", ""));
            } catch (NumberFormatException e) {
                throw new NumberEvaluationException(
                        "the number " + number + " in " + position + " position has an error"
                );
            }
        }
    }

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

    static class NumberEvaluationException extends RuntimeException {
        NumberEvaluationException(String message) {
            super(message);
        }
    }
}
