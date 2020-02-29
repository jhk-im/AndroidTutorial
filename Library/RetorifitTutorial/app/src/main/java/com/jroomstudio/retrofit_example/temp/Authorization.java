package com.jroomstudio.retrofit_example.temp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Authorization DTO   (권한부여)
 *
 **/

public class Authorization {

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("token")
    @Expose
    private String token;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
