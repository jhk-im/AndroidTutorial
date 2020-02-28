package com.jroomstudio.room_example;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* Room 에서 사용할 수 있는 Entity 가 되기위해서
* @Entity Annotation 추가
* */
@Entity
public class Todo {

    /*
    * id = primaryKEY
    * autoGenerate -> 자동으로 증가
    * */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;


    // 생성자
    public Todo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*
    * toString 재정의 -> 내용확인하기 위함
    * */
    @NonNull
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Todo{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
