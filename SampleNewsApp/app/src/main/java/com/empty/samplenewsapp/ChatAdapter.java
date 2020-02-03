package com.empty.samplenewsapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

// 안드로이드에서 RecyclerView 사용을 위해 Adapter 클래스를 제공한다.
// https://developer.android.com/guide/topics/ui/layout/recyclerview

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatData> mDataset;
    private String myNickName;

    // onCreate 안에 R.id.~~ 를 사용하여 연결된 activity.xml 에 셋팅되어있는
    // View 를 연결해주는 기능을 담당한다.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // onCreate 에서와 동일하게 각각의 뷰를 생성한다.
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public View rootView;


        public MyViewHolder(View v) {
            super(v);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            rootView = v;
        }
    }

    // Adapter 의 생성자이다.
    // 생성시 List<NewsData> 와 해당 액티비티의 context, 그리고 온클릭 리스너를 받아 셋팅한다.
    public ChatAdapter(List<ChatData> myDataset, Context context, String myNickName) {
        mDataset = myDataset;
        this.myNickName = myNickName;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh; // 끝으로 생성된 view 가 반환된다.
    }

    // 각각의 view 에 정보를 셋팅해주는 부분이다.
    // int position 으로 각각의 정보가 구분되어진다.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("setting","fuck");
        ChatData chat = mDataset.get(position);
        holder.TextView_msg.setText(chat.getMsg());
        holder.TextView_nickname.setText(chat.getNickName());

        if(chat.getNickName().equals(this.myNickName)){
            // 내 메세지는 오른쪽에서 보인다.
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else{
            // 다른 유저의 메세지는 왼쪽에서 보인다.
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }



    @Override
    public int getItemCount() {
        //삼항 연산자
        return mDataset == null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position) {
         return mDataset != null ? mDataset.get(position) : null;
    }

    // 채팅데이터 계속 추가 로직
    public void addChat(ChatData chat){
        mDataset.add(chat);
        // notifyItemInserted -> onBindViewHolder 에 몇번째에 들어가는지 알려주는 기능
        notifyItemInserted(mDataset.size()-1); // 갱신
    }
}