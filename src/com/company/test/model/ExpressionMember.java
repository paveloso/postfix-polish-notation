package com.company.test.model;

public class ExpressionMember {

    private String member;
    private int priority;
    private boolean isOperator;

    public ExpressionMember(String operandSign, int priority, boolean isOperator) {
        this.member = operandSign;
        this.priority = priority;
        this.isOperator = isOperator;

    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public void setOperator(boolean operator) {
        isOperator = operator;
    }
}
