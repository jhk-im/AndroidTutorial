package com.empty.samplenewsapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.util.List;

public class FaceActivity extends Activity {

    // Context 는 만능재주꾼
    // 앱 안에서 어디든 접근할 수있다.
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_face);
        mContext = this;

        // act_fae.xml 전체를 감싸고 있는 ConstraintLayout
        final ConstraintLayout ConstraintLayout_main = findViewById(R.id.ConstraintLayout_main);

        // Firebase 를 설정하고 이곳에 비트맵을 던져준다.
        FirebaseVisionFaceDetectorOptions highAccuracyOpts =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();

        // 비트맵 던져주기
        // act_face.xml 에 image view 에 셋팅된 이미지를 가져오는 것
        final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.redvelvet_irene);

        // 분석하라고 Firebase 에 넘김
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(highAccuracyOpts);
        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {
                                        // Task completed successfully
                                        Log.d("FACES",faces.toString());

                                        // 안드로이드 해상도 문제로 firebase 포지션값을 그대로 가져오면 약간 틀어진다.
                                        // 그것을 맞춰주는 로직
                                        Point p = new Point();
                                        Display display = getWindowManager().getDefaultDisplay();
                                        display.getSize(p);

                                        // 1:10 = 10:x
                                        //

                                        for (FirebaseVisionFace face : faces) {

                                            // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
                                            // nose available):

                                            // 얼굴중에서 눈의 랜드마크 즉, 눈의 위치를 찾아준다.
                                            FirebaseVisionFaceLandmark leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE);
                                            float lex = leftEye.getPosition().getX();
                                            float ley = leftEye.getPosition().getY();
                                            // 얼굴중에서 왼쪽 볼
                                            FirebaseVisionFaceLandmark leftCheeck = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK);
                                            float lcx = leftCheeck.getPosition().getX();
                                            float lcy = leftCheeck.getPosition().getY();
                                            // 얼굴중에서 오른쪽 볼
                                            FirebaseVisionFaceLandmark rightCheeck = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK);
                                            float rcx = rightCheeck.getPosition().getX();
                                            float rcy = rightCheeck.getPosition().getY();

                                            // 이미지뷰 가상으로 만들고 이미지 셋팅하기
                                            ImageView imageViewLeftEye = new ImageView(mContext);
                                            imageViewLeftEye.setImageResource(R.drawable.star);
                                            // 이미지 틀어진것 계산식
                                            imageViewLeftEye.setX(p.x * lex / bitmap.getWidth() - 100);
                                            imageViewLeftEye.setY(p.y * ley / bitmap.getHeight() - 100);
                                            // 이미지 크기 지정
                                            imageViewLeftEye.setLayoutParams(new ConstraintLayout.LayoutParams(200,200));
                                            ConstraintLayout_main.addView(imageViewLeftEye);

                                            ImageView imageViewLeftCheek = new ImageView(mContext);
                                            imageViewLeftCheek.setImageResource(R.drawable.bts_character);
                                            imageViewLeftCheek.setX(p.x * lcx / bitmap.getWidth() - 100);
                                            imageViewLeftCheek.setY(p.y * lcy / bitmap.getHeight() - 100);
                                            imageViewLeftCheek.setLayoutParams(new ConstraintLayout.LayoutParams(200,200));
                                            ConstraintLayout_main.addView(imageViewLeftCheek);

                                            ImageView imageViewRightCheek = new ImageView(mContext);
                                            imageViewRightCheek.setImageResource(R.drawable.kiss_lip);
                                            imageViewRightCheek.setX(p.x * rcx / bitmap.getWidth() - 100);
                                            imageViewRightCheek.setY(p.y * rcy / bitmap.getHeight() - 100);
                                            imageViewRightCheek.setLayoutParams(new ConstraintLayout.LayoutParams(200,200));
                                            ConstraintLayout_main.addView(imageViewRightCheek);
                                        }

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });

    }
}
