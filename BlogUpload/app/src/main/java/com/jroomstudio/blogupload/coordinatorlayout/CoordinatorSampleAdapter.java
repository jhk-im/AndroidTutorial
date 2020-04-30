package com.jroomstudio.blogupload.coordinatorlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jroomstudio.blogupload.R;

import java.util.List;

public class CoordinatorSampleAdapter extends RecyclerView.Adapter<CoordinatorSampleAdapter.ItemViewHolder> {

    private List<ListItem> mLists;

    public CoordinatorSampleAdapter(List<ListItem> lists){
        mLists = lists;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.activity_coordinator_item, parent, false) ;
        return new ItemViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
        }
        public void onBind(ListItem item){
            number.setText(item.getTitle());
        }
    }
}
