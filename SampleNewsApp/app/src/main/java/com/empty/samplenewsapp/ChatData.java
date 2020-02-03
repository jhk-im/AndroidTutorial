package com.empty.samplenewsapp;

import java.io.Serializable;

// Serializable 을 상속 받으면
// Intent 에서 값을 넘길 때 class 자체를 넘길 수 있다.

public class ChatData {

    // 각각 title , urlToImage, description 을 셋팅한다.
    private  String msg;
    private  String nickName;

    // 입력과 호출은 getter setter 를 사용한다.
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
