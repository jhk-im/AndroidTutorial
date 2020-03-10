package com.jroomstudio.junitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView mTextViewCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextViewCoffee = (TextView) findViewById(R.id.textView_coffee);
        String count = getIntent().getStringExtra("count");
        Float total = getIntent().getFloatExtra("total",0f);
        mTextViewCoffee.setText("count : "+count+ "\n" +
                "total : "+total);
    }
}
