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
    }

    public int evaluate(String expression) {
        int index = findIndexOfOperation(expression);
        int result = parseFirstArgument(expression, index);
        if (hasMoreArguments(expression, index)) {
            char sign = expression.charAt(index);
            if (sign == '+') {
                result += parseNextArgument(expression, index + 1);
            } else if (sign == '-') {
                result -= parseNextArgument(expression, index + 1);
            } else {
                result *= parseNextArgument(expression, index + 1);
            }
        }
        return result;
    }

    private boolean hasMoreArguments(String expression, int index) {
        return index < expression.length();
    }

    private int parseFirstArgument(String expression, int index) {
        return Integer.parseInt(expression.substring(0, index));
    }

    private int findIndexOfOperation(String expression) {
        int index = 0;
        while (index < expression.length() && !operationSet.contains(expression.charAt(index))) {
            index += 1;
        }
        return index;
    }

    private int parseNextArgument(String expression, int from) {
        return Integer.parseInt(expression.substring(from));
    }
}
