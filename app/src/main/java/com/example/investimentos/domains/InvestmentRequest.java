package com.example.investimentos.domains;

import com.example.investimentos.utils.DateState;

import java.io.Serializable;
import java.util.Date;

public class InvestmentRequest implements Serializable {
    private static final String INVESTMENT_TYPE = "CDI";
    private final double rate;
    private final double investedAmount;
    private final Date maturityDate;
    private final boolean isTaxFree;
    private final String index;

    public InvestmentRequest(double rate, Date maturityDate, double investedAmount) {
        this.rate = rate;
        this.maturityDate = maturityDate;
        this.investedAmount = investedAmount;
        this.isTaxFree = false;
        this.index = INVESTMENT_TYPE;
    }

    public DateState isValidDueDate() {
        if (maturityDate == null) {
            return DateState.INVALID_FORMAT;
        }

        Date currentDate = new Date();
        if (maturityDate.after(currentDate)) {
            return DateState.VALID;
        } else {
            return DateState.INVALID_DUE_DATE;
        }
    }
}
