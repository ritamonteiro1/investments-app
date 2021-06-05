package com.example.investimentos.api;

import android.telecom.Call;

import com.example.investimentos.domains.InvestmentResult;

import retrofit2.http.GET;


public interface DataService {

    @GET
    Call<InvestmentResult> recoverInvestmentResult() {
    }
}
