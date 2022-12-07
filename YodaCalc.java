import java.util.*;

public class YodaCalc {
    /**
     * This method takes in the input string, 
     * evaluates the math expression, and
     * returns the result as a decimal value.
     * 
     * @param   input   the input math string,
     *                  supporting: +, -, *, /, (, )
     * @return          the evaluated result
     */
    public static double calc(String input) {
        Stack<Character> convertToPostfix = new Stack<Character>();
        String redefinedInput = "";
        String postFixOutput = "";

        //this for loop takes the original output and converts it to no spaces
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equals(" ")) {
                redefinedInput += "";
            }
            else {
                redefinedInput += input.substring(i, i + 1);
            }
        }

        //this will be the logic on how we convert to post fix with the redefinedinput
        for (int i = 0; i < redefinedInput.length(); i++) {
            char temp = redefinedInput.charAt(i);

            if (Character.isLetterOrDigit(temp)) {
                postFixOutput += temp;
            }

            else if (temp == '(') { // If the scanned character is an '(', push to the stack
                convertToPostfix.push(temp);
            }

            // If the scanned character is an ')',
            // pop and output from the stack
            // until an '(' is encountered.
            else if (temp == ')' ) { 
                while (!convertToPostfix.isEmpty() && convertToPostfix.peek() != '(') {
                    postFixOutput += " " + convertToPostfix.peek();
                    convertToPostfix.pop();
                }
                convertToPostfix.pop();
            }

            else { // an operator is encountered
                while (!convertToPostfix.isEmpty() && precedence(temp) <= precedence(convertToPostfix.peek())) {
                    postFixOutput += " " + convertToPostfix.peek();
                    convertToPostfix.pop();
                }
                postFixOutput += " ";
                convertToPostfix.push(temp);
            }
        }
        while (!convertToPostfix.isEmpty()) {
            postFixOutput += " " + convertToPostfix.peek();
            convertToPostfix.pop();
        }

        return evaluateExpression(postFixOutput);
    }

    /**
     * This method will evaluate the psot fix expression 
     * and return the result of the equation
     * @param postFixExpression the expression we will solve
     * 
     * @return the evaluated expression of the equation
     */
    public static double evaluateExpression(String postFixExpression) {
        Stack<Double> result = new Stack<Double>();

        for (int i = 0; i < postFixExpression.length(); i++) {
            char temp = postFixExpression.charAt(i);

            if (temp == ' ') {
                continue;
            }

            else if (Character.isDigit(temp)) {
                double j = 0.0;
                while (Character.isDigit(temp)) {
                    j = j*10 + (double)(temp - '0');
                    i++;
                    temp = postFixExpression.charAt(i);
                }
                i--;

                result.push(j);
            }

            else {
                double var1 = result.pop();
                double var2 = result.pop();
                
                if (temp == '+')
                    result.push(var2 + var1);
                else if (temp == '-')
                    result.push(var2 - var1);
                else if (temp == '*')
                    result.push(var2 * var1);
                else if (temp == '/')
                    result.push(var2 / var1);
            }
        }
        return result.pop();
    }

    /**
     * This method takes in an operator and
     * decides its precedence over another operator
     * to convert into postfix notation.
     * 
     * @param operator      the operator at scanned position in the string
     * @return              an int of order of precedence
     */
    public static int precedence(char operator) {
        if (operator == '^')
            return 3;
        else if (operator == '/' || operator == '*')
            return 2;
        else if (operator == '+' || operator == '-')
            return 1;
        else
            return -1;
    }
    
    public static void main(String[] args) {
        System.out.println(calc("(4   +  9)  / 13"));
    }
}
