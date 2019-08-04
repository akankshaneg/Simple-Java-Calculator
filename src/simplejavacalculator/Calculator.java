package simplejavacalculator;

import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {
    //TODO use for expression implementation
    private TokenStack operatorStack;
    private TokenStack valueStack;
    private boolean error;
    public Calculator()

    {
        operatorStack = new TokenStack();
        valueStack = new TokenStack();
        error = false;
    }
    public enum BiOperatorModes {

        normal, add, minus, multiply, divide, xpowerofy
    }

    public enum MonoOperatorModes {

        square, squareRoot, oneDividedBy, cos, sin, tan, log, rate, abs, mem, memRcl, memClr, bksp, decimal
    }

    public Double num1, num2, memNum;
    public BiOperatorModes mode = BiOperatorModes.normal;

    public Double calculateBiImpl() {
        if (mode == BiOperatorModes.normal) {
            return num2;
        }
        if (mode == BiOperatorModes.add) {
            return num1 + num2;
        }
        if (mode == BiOperatorModes.minus) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.multiply) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.divide) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.xpowerofy) {
            return pow(num1, num2);
        }

        // never reach
        throw new Error();
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return Double.NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return Double.NaN;
    }
    //public Double calculatExpression(){


    //}

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        if (newMode == MonoOperatorModes.square) {
            return num * num;
        }
        if (newMode == MonoOperatorModes.squareRoot) {
            return Math.sqrt(num);
        }
        if (newMode == MonoOperatorModes.oneDividedBy) {
            return 1 / num;
        }
        if (newMode == MonoOperatorModes.cos) {
            return Math.cos(num);
        }
        if (newMode == MonoOperatorModes.sin) {
            return Math.sin(num);
        }
        if (newMode == MonoOperatorModes.tan) {
            return Math.tan(num);
        }
        if (newMode == MonoOperatorModes.log) {
            return log10(num);
        }
        if (newMode == MonoOperatorModes.rate) {
            return num / 100;
        }
        if (newMode == MonoOperatorModes.abs) {
            return Math.abs(num);
        }

        // new code - memory function - store & return the value
        if (newMode == MonoOperatorModes.mem) {
            memNum = num;
            return memNum;
        }

        // new code - memory recall function - return the stored value
        if (newMode == MonoOperatorModes.memRcl) {
            if (memNum != null) {
                return memNum;
            } else {
                return num;
            }
        }

        // new code - memory clear function - clear the stored value
        if (newMode == MonoOperatorModes.memClr) {
            memNum = 0.0;
            return memNum;
        }

        // new code - Backspace functionality - return the current value
        if (newMode == MonoOperatorModes.bksp) {
            return num;
        }

        // never reach
        throw new Error();
    }


    //Methods to perform expression evaluation-START

    //TODO This function is implemented to process input expression by breaking them into Token objects
    // and then calling process Operator to use stack and evaluate the expression. This function needs to be called when user presses expression button
    // and inputs an expression to evaluate.
    public String processInput(String input) {
        // The tokens from expression
        String[] parts = input.split(" ");
        Token[] tokens = new Token[parts.length];
        for (int n = 0; n < parts.length; n++) {
            tokens[n] = new Token(parts[n]);
        }

        // Main loop - process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            Token nextToken = tokens[n];
            if (nextToken.getType() == Token.NUMBER) {
                valueStack.push(nextToken);
            } else if (nextToken.getType() == Token.OPERATOR) {
                if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
                    operatorStack.push(nextToken);
                } else {
                    while (!operatorStack.isEmpty() && nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
                        Token toProcess = operatorStack.top();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(nextToken);
                }
            } else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(nextToken);
            } else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
                while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
                    Token toProcess = operatorStack.top();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
                    operatorStack.pop();
                } else {
                    return "Error: unbalanced parenthesis";

                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
            Token toProcess = operatorStack.top();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.
        if(error == false) {
            Token result = valueStack.top();
            valueStack.pop();
            if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
                return "Expression error.";
            } else {
                return String.valueOf(result.getValue());
            }
        }
        else {
            return "";
        }

        }
    private void processOperator(Token t) {

        Token A = null, B = null;
        if (valueStack.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            B = valueStack.top();
            valueStack.pop();
        }
        if (valueStack.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            A = valueStack.top();
            valueStack.pop();
        }
        Token R = t.operate(A.getValue(), B.getValue());
        valueStack.push(R);
    }
    //Methods to perform expression evaluation-END



    }



