package com.jroomstudio.blogupload.coordinatorlayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jroomstudio.blogupload.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    public List<ListItem> mList = new ArrayList<ListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);


        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);
        ActionBar ab2 = getSupportActionBar();
        ab2.setDisplayHomeAsUpEnabled(true);
        // 툴바 셋팅
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = (RecyclerView) findViewById(R.id.bible_recycler_view);
        addItem("0","0");
        addItem("1","1");
        addItem("2","2");
        addItem("3","3");
        addItem("4","4");
        CoordinatorSampleAdapter adapter = new CoordinatorSampleAdapter(mList);
        rv.setAdapter(adapter);
    }

    public void addItem(String title, String desc){
        ListItem item = new ListItem();
        item.setTitle(title);
        item.setDesc(desc);
        mList.add(item);
    }
}
