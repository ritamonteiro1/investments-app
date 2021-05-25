package com.example.easynvest;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvestmentResult {
    private final Investment investment;
    private static final float irInvestment = 0.10f;
    private static final int PERCENTAGE_DENOMINATOR = 100;
    private static final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public InvestmentResult(Investment investment) {
        this.investment = investment;
    }

    public Investment getInvestment() {
        return investment;
    }

    public float incomeValue(){
        return investment.getMoney() * investment.getCdiPercentage()/PERCENTAGE_DENOMINATOR;
    }

    public float grossValue(){
        return incomeValue()+investment.getMoney();
    }

    public float irValue(){
        return irInvestment * incomeValue();
    }

    public float netInvestmentValue(){
        return grossValue() - irValue();
    }

    public String convertDateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    public String convertFloatToMoney(float value){
        return numberFormat.format(value);
    }
}
