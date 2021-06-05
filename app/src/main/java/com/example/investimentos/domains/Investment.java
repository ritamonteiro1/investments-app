package com.example.investimentos.domains;

import com.example.investimentos.utils.DateState;

import java.io.Serializable;
import java.util.Date;

public class Investment implements Serializable {
    private final float rate;
    private final float investedAmount;
    private final Date maturityDate;

    public Investment(float rate, Date maturityDate, float investedAmount) {
        this.rate = rate;
        this.maturityDate = maturityDate;
        this.investedAmount = investedAmount;
    }

    public float getRate() {
        return rate;
    }

    public float getInvestedAmount() {
        return investedAmount;
    }

    public Date getMaturityDate() {
        return maturityDate;
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
