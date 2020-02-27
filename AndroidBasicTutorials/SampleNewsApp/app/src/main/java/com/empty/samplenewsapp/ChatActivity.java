package com.empty.samplenewsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick = "nick1";

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList,ChatActivity.this, nick);
        mRecyclerView.setAdapter(mAdapter);
        // 문제해결 사이트
        // http://www.chansek.com/RecyclerView-no-adapter-attached-skipping-layout/

        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);

        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String msg = EditText_chat.getText().toString();

               if(msg != null){
                   // message 아래에 해당 값을 입력한다.
                   ChatData chat = new ChatData();
                   chat.setNickName(nick);
                   chat.setMsg(msg);
                   myRef.push().setValue(chat);
               }
            }
        });



        // 파이어베이스 데이터베이스를 생성하고 할당했다.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // 리얼타임 데이터베이스에 message 를 참조해온다.
        myRef = database.getReference();

        // message 아래에 해당 값을 입력한다.
/*        ChatData chat = new ChatData();
        chat.setNickName(nick);
        chat.setMsg("hi");
        myRef.setValue(chat);*/

        // 1. recyclerView - 반복
        // 1-1. chat data
        // message, nickname, isMine - Data Transfer Object

        // 2. DB 내용을 넣는다.

        // 3. 채팅 가져오기
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.d("CHAT",dataSnapshot.getValue().toString());
                ChatData chat =  dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
