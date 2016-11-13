package calc;

public class Evaluator {

    public int evaluate(String expression) {
        int index = 0;
        while (index < expression.length() && expression.charAt(index) != '+') {
            index += 1;
        }
        int result = Integer.parseInt(expression.substring(0, index));
        if (index < expression.length()) {
            result += Integer.parseInt(expression.substring(index + 1));
        }
        return result;
    }
}
