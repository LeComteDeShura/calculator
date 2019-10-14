
package ru;

import java.util.Stack;

public class Calculator{

    public Calculator(String equation){
        this.equation = equation;
    }

    public int binaryOperations(int n1, int n2, String operation){
        int result =1;
        if (operation.equals("+")) {
            result = n1 + n2;
        }

        if (operation.equals("-")) {
            result = n1 - n2;
        }

        if (operation.equals("*")) {
            result =  n1 * n2;
        }

        if (operation.equals("/")) {
            result = n1 / n2;
        }
        return result;
    }

    public boolean isDigit(String str){
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public int getPriorityOperations(String operation){
        switch (operation) {
            case "+":
                return 1;

            case "-":
                return 1;

            case "/":
                return 2;

            case "*":
                return 2;


        }
        return 0;
    }

    public String getPostfixNotation(String infix){
        Stack<String> stack = new Stack<>();
        String postfix = "";
        String[] infixParts = infix.split(" ");
        String[] string = {"(", ")"};

        for (int i = 0; i < infixParts.length ; i++) {
            if (isDigit(infixParts[i])) {
                postfix += infixParts[i] + " ";
                continue;
            }

            if (infixParts[i].equals("(")) {
                stack.push(infixParts[i]);
                continue;
            }
            if (infixParts[i].equals(")")) {
                while (!stack.peek().equals("(")) {
                    postfix += stack.pop() + " ";
                }
                stack.pop();
                continue;
            }

            if (stack.empty()) {
                stack.push(infixParts[i]);
                continue;
            }

            else {
                if (stack.peek().equals("(")) {
                    stack.push(infixParts[i]);
                    continue;
                }

                if (getPriorityOperations(stack.peek()) > getPriorityOperations(infixParts[i])
                    | getPriorityOperations(stack.peek()) == getPriorityOperations(infixParts[i])) {
                    postfix += stack.pop() + " ";
                    stack.push(infixParts[i]);
                }

                else {
                    stack.push(infixParts[i]);
                }
            }
        }
        while(!stack.empty()){
            postfix += stack.pop() + " ";
        }


        return postfix;
    }

    public String сalculationPostfixEquation (String postfix){
        String[] postfixParts = postfix.split(" ");
        Stack<String> stack = new Stack<>();
        int result = 0;

        int i = 0;
        while(postfixParts.length != i){
            if (isDigit(postfixParts[i])) {
                stack.push(postfixParts[i]);
                i++;
                continue;
            }

            else {
                int n2 = Integer.parseInt(stack.pop());
                int n1 = Integer.parseInt(stack.pop());

                result = binaryOperations(n1, n2, postfixParts[i]);

                stack.push(Integer.toString(result));
                i++;
            }
        }
        return stack.pop();

    }


    String equation;
}
