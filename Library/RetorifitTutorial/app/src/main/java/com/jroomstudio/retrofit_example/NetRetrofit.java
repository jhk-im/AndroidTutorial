package com.jroomstudio.retrofit_example;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    private static NetRetrofit ourInstance = new NetRetrofit();

    public static NetRetrofit getInstance() { return ourInstance; }

    private NetRetrofit(){ }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }

}
