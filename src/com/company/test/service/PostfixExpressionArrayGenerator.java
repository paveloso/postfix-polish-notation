package com.company.test.service;

import com.company.test.model.ExpressionMember;

import java.util.Stack;

public class PostfixExpressionArrayGenerator implements ExpressionArrayGenerator {

    @Override
    public ExpressionMember[] generateString(String expression) {

        String[] expressionMembers = expression.split(" ");
        ExpressionMember[] finalPostfixExpression = new ExpressionMember[expressionMembers.length];
        Stack<ExpressionMember> operators = new Stack<>();

        int finalExpressionIndexCurrent = 0;

        boolean isPrevMemberNumber = false;
        for (int i = 0; i < expressionMembers.length; i++) {
            if (expressionMembers[i].contains(".")) {
                Double.parseDouble(expressionMembers[i]);
                if (isPrevMemberNumber) {
                    throw new IllegalArgumentException("Неверно создано выражение!");
                }
                finalPostfixExpression[finalExpressionIndexCurrent] = new ExpressionMember(expressionMembers[i], 0, false);
                finalExpressionIndexCurrent++;
                isPrevMemberNumber = true;
            } else {
                try {
                    Integer.parseInt(expressionMembers[i]);
                    if (isPrevMemberNumber) {
                        throw new IllegalArgumentException("Неверно создано выражение!");
                    }
                    finalPostfixExpression[finalExpressionIndexCurrent] = new ExpressionMember(expressionMembers[i], 0, false);
                    finalExpressionIndexCurrent++;
                    isPrevMemberNumber = true;
                } catch (NumberFormatException nfe) {
                    if (!isPrevMemberNumber) {
                        throw new IllegalArgumentException("Неверно создано выражение! Слишком много операторов подряд.");
                    }
                    ExpressionMember currentOperator = null;
                    currentOperator = getPreparedOperator(expressionMembers[i]);
                    if (operators.empty()) {
                        operators.push(currentOperator);
                    } else {
                        if (currentOperator.getPriority() > operators.peek().getPriority()) {
                            operators.push(currentOperator);
                        } else {
                            finalPostfixExpression[finalExpressionIndexCurrent] = operators.pop();
                            finalExpressionIndexCurrent++;
                            operators.push(currentOperator);
                        }
                    }
                    isPrevMemberNumber = false;
                }
            }
        }

        if (!operators.empty()) {
            while (!operators.empty()) {
                finalPostfixExpression[finalExpressionIndexCurrent] = operators.pop();
                finalExpressionIndexCurrent++;
            }
        }

        return finalPostfixExpression;
    }

    private ExpressionMember getPreparedOperator(String operator) {
        ExpressionMember curOperator = null;
        if (operator.equals("+") || operator.equals("-")) {
            curOperator = new ExpressionMember(operator, 1, true);
        }
        if (operator.equals("*") || operator.equals("/")) {
            curOperator = new ExpressionMember(operator, 2, true);
        }
        if (operator.equals("(") || operator.equals(")")) {
            curOperator = new ExpressionMember(operator, 3, true);
        }

        if (curOperator == null) {
            throw new IllegalArgumentException("Неверный операнд!");
        }

        return curOperator;
    }
}
