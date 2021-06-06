package com.example.investimentos.domains;

public class InvestmentResponse {

    private InvestmentParameterResponse investmentParameter;
    private Double grossAmount;
    private Double netAmount;
    private Double grossAmountProfit;
    private Double netAmountProfit;

    public InvestmentParameterResponse getInvestmentParameterResponse() {
        return investmentParameter;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public Double getGrossAmountProfit() {
        return grossAmountProfit;
    }

    public Double getNetAmountProfit() {
        return netAmountProfit;
    }
}
