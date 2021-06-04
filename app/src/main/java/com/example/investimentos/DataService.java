package com.example.investimentos;

import android.telecom.Call;

public interface DataService {

    @GET
    default Call<InvestmentResult> recoverInvestmentResult() {
    }
}
