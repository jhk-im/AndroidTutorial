package com.jroomstudio.jsoupsample.jsoupsample2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jroomstudio.jsoupsample.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<ItemObject> mList;

    private Context mContext;

    //생성자
    public MovieAdapter(ArrayList<ItemObject> list){
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle.setText(String.valueOf(mList.get(position).getTitle()));
        holder.tvRelease.setText(String.valueOf(mList.get(position).getRelease()));
        holder.tvDirector.setText(String.valueOf(mList.get(position).getDirector()));

        Glide.with(mContext).load(mList.get(position).getImg_url()).into(holder.iv_movie);

        }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_movie;
        private TextView tvTitle, tvRelease, tvDirector;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_movie = (ImageView) itemView.findViewById(R.id.iv_movie);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvRelease = (TextView) itemView.findViewById(R.id.tv_release);
            tvDirector = (TextView) itemView.findViewById(R.id.tv_director);
        }
    }
}
