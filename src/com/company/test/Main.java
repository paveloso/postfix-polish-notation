package com.company.test;

import com.company.test.calculator.Calculator;
import com.company.test.calculator.PostfixCalculator;
import com.company.test.model.ExpressionMember;
import com.company.test.service.PostfixExpressionArrayGenerator;
import com.company.test.service.ExpressionArrayGenerator;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner expressionReader = new Scanner(new InputStreamReader(System.in));
        String consoleExpression = expressionReader.nextLine();

        ExpressionArrayGenerator sg = new PostfixExpressionArrayGenerator();
        ExpressionMember[] posfix = sg.generateString(consoleExpression);

        Calculator calculator = new PostfixCalculator();
        double result = calculator.calculate(posfix);
        System.out.println(new BigDecimal(result).setScale(1, RoundingMode.HALF_UP));
    }
}
