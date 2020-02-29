package com.jroomstudio.retrofit_example.temp;

import com.google.gson.annotations.SerializedName;

/**
 * Result DTO  (Data Transfer Object)
 * DTO ?
 * 기본적으로 레트로핏은 ResponseBody 를 통해 서버가 전해주는 Response 를 받을 수 있다.
 * ResponseBody 를 통해 받게되면 , 파싱을 일일이 해야한다.
 * ex) jsonObject json = JsonParser.parse(...
 * DTO 클래스를 지정해두고
 * addConvertFactory(GsonConverterFactory.create()) 를 통해서
 * 지정한 DTO 클래스대로 자동으로 파싱을 요청하여 사용하는 것이다.
 *
**/

public class Result {

    /**
     * SerializedName
     * -> 서버가 전달해주는 규격과 맞추기 위함.
     * 서버와 클라이언트간에 변수명이 같으면 상관 없지만
     * 다를 경우 서버에 맞춰 규격을 맞춰주는 것이 좋다.
     **/

    // api Response 시 들어올 name 이라는 json key
    @SerializedName("name")
    private String name;

    // api Response 시 들어올 age 라는 json key
    @SerializedName("age")
    private int age;

    /**
     * 반드시 getter setter 선언해주어야 함
     **/

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
