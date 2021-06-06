package com.example.investimentos.domains;

import java.util.Date;

public class InvestmentParameterResponse {
    private Double investedAmount;
    private Double rate;
    private Date maturityDate;

    public Double getInvestedAmount() {
        return investedAmount;
    }

    public Double getRate() {
        return rate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }
}
