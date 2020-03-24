package com.jroomstudio.blogupload.chip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jroomstudio.blogupload.R;

public class ChipActivity extends AppCompatActivity {

    ChipGroup chipGroup;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip);

        chipGroup = (ChipGroup) findViewById(R.id.chip_group);

        for(int i = 0; i<5; i++){
            // Chip 인스턴스 생성
            Chip chip = new Chip(ChipActivity.this);
            // Chip 의 텍스트 지정
            chip.setText("chip"+i);
            // 체크 표시 사용 여부
            chip.setCheckable(true);
            // chip close 아이콘 이미지 지정
            chip.setCloseIcon(getDrawable(R.drawable.ic_close));
            // close icon 표시 여부
            chip.setCloseIconVisible(true);
            // chip group 에 해당 chip 추가
            chipGroup.addView(chip);

            // chip 인스턴스 클릭 리스너
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ChipActivity.this, "Check", Toast.LENGTH_SHORT).show();
                }
            });

            // chip 인스턴스 close 버튼 클릭 리스너
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chipGroup.removeView(v);
                }
            });
        }
        }
}
