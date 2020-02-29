package com.jroomstudio.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString();

                if (!id.isEmpty()) {
                    Call<ArrayList<JsonObject>> res = NetRetrofit.getInstance().getService().getListRepos(id);
                    res.enqueue(new Callback<ArrayList<JsonObject>>() {
                        @Override
                        public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                            Log.d("Retrofit", response.toString());
                            if (response.body() != null)
                                textView.setText(response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                            Log.e("Err", t.getMessage());
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



}
