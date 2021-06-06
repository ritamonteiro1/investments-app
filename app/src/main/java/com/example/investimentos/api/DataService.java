package com.example.investimentos.api;


import com.example.investimentos.domains.InvestmentResponse;


import retrofit2.Call;
import retrofit2.http.GET;


public interface DataService {

    @GET("cdec3139-b2ad-430b-b6a8-27cff7b12a0d")
    Call<InvestmentResponse> recoverInvestmentResult();
}
