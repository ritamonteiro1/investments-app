package com.example.investimentos;

public class InvestmentResult {
    private final Investment investment;
    private static final float IR_INVESTMENT_PERCENTAGE = 0.10f;
    private static final int DENOMINATOR_PERCENTAGE = 100;

    public InvestmentResult(Investment investment) {
        this.investment = investment;
    }

    public Investment getInvestment() {
        return investment;
    }

    public float incomeValue() {
        return investment.getMoney() * investment.getCdiPercentage() / DENOMINATOR_PERCENTAGE;
    }

    public float grossValue() {
        return incomeValue() + investment.getMoney();
    }

    public float irValue() {
        return IR_INVESTMENT_PERCENTAGE * incomeValue();
    }

    public float netInvestmentValue() {
        return grossValue() - irValue();
    }
}
