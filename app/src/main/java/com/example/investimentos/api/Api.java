package com.example.investimentos.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private static final String url = "https://run.mocky.io/v3/ecfaebf5-782b-4b24-ae4f-23b5c3a861da";

    private Retrofit setupRetrofit() {
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
