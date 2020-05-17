package com.company.test.calculator;

import com.company.test.model.ExpressionMember;

import java.util.Stack;

public class PostfixCalculator implements Calculator {

    @Override
    public double calculate(ExpressionMember[] expressionMembersArray) {
        Stack<ExpressionMember> operands = new Stack<>();
        for (int i = 0; i < expressionMembersArray.length; i++) {
            if (expressionMembersArray[i].isOperator()) {
                ExpressionMember operator = expressionMembersArray[i];
                ExpressionMember topMember = operands.pop();
                ExpressionMember prevTopMember = operands.pop();
                operands.push(getArithmeticActionForOperator(operator, prevTopMember, topMember));
            } else {
                operands.push(expressionMembersArray[i]);
            }
        }
        return Double.parseDouble(operands.pop().getMember());
    }

    private ExpressionMember getArithmeticActionForOperator(ExpressionMember operator, ExpressionMember a, ExpressionMember b) {
        if (operator.getMember().equals("+")) {
                return new ExpressionMember(String.valueOf(Double.parseDouble(a.getMember()) + Double.parseDouble(b.getMember())), 0, false);
        }
        if (operator.getMember().equals("-")) {
            return new ExpressionMember(String.valueOf(Double.parseDouble(a.getMember()) - Double.parseDouble(b.getMember())), 0, false);
        }
        if (operator.getMember().equals("*")) {
            return new ExpressionMember(String.valueOf(Double.parseDouble(a.getMember()) * Double.parseDouble(b.getMember())), 0, false);
        }
        if (operator.getMember().equals("/")) {
            return new ExpressionMember(String.valueOf(Double.parseDouble(a.getMember()) / Double.parseDouble(b.getMember())), 0, false);
        }
        return null;
    }
}
