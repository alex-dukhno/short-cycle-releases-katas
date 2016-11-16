package calc;

import java.util.HashSet;
import java.util.Set;

public class Evaluator {

    private final Set<Character> operationSet;

    public Evaluator() {
        operationSet = new HashSet<>();
        operationSet.add('+');
        operationSet.add('-');
        operationSet.add('*');
        operationSet.add('/');
    }

    public double evaluate(String expression) {
        int index = findIndexOfOperation(expression);
        double result = parseFirstArgument(expression, index);
        if (hasMoreArguments(expression, index)) {
            char sign = expression.charAt(index);
            if (sign == '+') {
                result += parseNextArgument(expression, index + 1);
            } else if (sign == '-') {
                result -= parseNextArgument(expression, index + 1);
            } else if (sign == '*') {
                result *= parseNextArgument(expression, index + 1);
            } else {
                result /= parseNextArgument(expression, index + 1);
            }
        }
        return result;
    }

    private boolean hasMoreArguments(String expression, int index) {
        return index < expression.length();
    }

    private double parseFirstArgument(String expression, int index) {
        return Double.parseDouble(expression.substring(0, index));
    }

    private int findIndexOfOperation(String expression) {
        int index = 0;
        while (index < expression.length() && !operationSet.contains(expression.charAt(index))) {
            index += 1;
        }
        return index;
    }

    private double parseNextArgument(String expression, int from) {
        return Double.parseDouble(expression.substring(from));
    }
}
