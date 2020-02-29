package com.jroomstudio.retrofit_example;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("users/{users}/repos")
    Call<ArrayList<JsonObject>> getListRepos(@Path("users") String id);

}
