package calc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Evaluator {

    private final Map<Character, BiFunction<Double, Expression, Double>> operationFunction;
    private final Set<Character> numberChars;

    public Evaluator() {
        numberChars = new HashSet<>();

        numberChars.add('0');
        numberChars.add('1');
        numberChars.add('2');
        numberChars.add('3');
        numberChars.add('4');
        numberChars.add('5');
        numberChars.add('6');
        numberChars.add('7');
        numberChars.add('8');
        numberChars.add('9');
        numberChars.add('.');
        numberChars.add('_');

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
        }
        else {
            int start = exp.index;
            while (exp.hasMoreArguments() && numberChars.contains(exp.getCurrentChar()) && exp.getCurrentChar() != ')') {
                exp.index += 1;
            }
            return Double.parseDouble(exp.src.substring(start, exp.index).replaceAll("_", ""));
        }
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
