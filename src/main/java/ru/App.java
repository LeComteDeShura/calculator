
package ru;

public  class App{


    public static void main(String[] args) {
        String infix = "( 6 + 10 - 4 ) / 4 + 8 * 2 + 9";
        String postfix;
        String result;
        Calculator calculator = new Calculator(infix);

        //System.out.println(args[0]);
        postfix = calculator.getPostfixNotation(infix);
        //System.out.println(postfix);

        result = calculator.сalculationPostfixEquation(postfix);
        System.out.println("Answer: " + result);
    }
}
