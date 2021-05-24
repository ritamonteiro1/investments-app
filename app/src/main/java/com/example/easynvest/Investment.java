package com.example.easynvest;

import java.io.Serializable;
import java.util.Date;

public class Investment implements Serializable {
    private final float cdiPercentage;
    private final float money;
    private Date dueDate;

    public Investment(float cdiPercentage, Date dueDate, float money) {
        this.cdiPercentage = cdiPercentage;
        this.dueDate = dueDate;
        this.money = money;
    }

    public float getCdiPercentage() {
        return cdiPercentage;
    }

    public float getMoney() {
        return money;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public DateState isValidDueDate() {
        if (dueDate == null) {
            return DateState.INVALID_FORMAT;
        }

        Date currentDate = new Date();
        if (dueDate.after(currentDate)) {
            return DateState.VALID;
        } else {
            return DateState.INVALID_DUE_DATE;
        }
    }


}
