package com.jroomstudio.librarytutorials;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView_test,imageView_bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * toJson() 메소드를 이용하여
         * 자바 오브젝트 -> JSON 변환 (Serialization)
         * toJson(colors)
         **/
        Map<Integer, String> colors = new HashMap<>();
        colors.put(1, "blue");
        colors.put(2, "yellow");
        colors.put(3, "green");

        Gson gson = new Gson();
        String output = gson.toJson(colors);

        Log.e("color-gson",output);


        /*
         * fromJson 메소드를 이용하여
         * SON -> javaObject 변환  (Deserialization)
         * fromJson(json_string,User.class)
         * */
        String json_string = "{\"firstName\":\"Mayer\", \"lastName\": \"John\"}";

        Gson gson2 = new Gson();
        User user = gson2.fromJson(json_string, User.class);

        Log.e("user-firstname",user.getFirstName());
        Log.e("user-lastname",user.getLastName());


        /*
         * JSON 데이터 파싱하기 - 응용
         * 1. AlbumVO 를 담는 ArrayList 생성
         * 2. Gson 객체 생성
         * 3. try catch
         * 4. InputStream 생성하고 album.json 의 내용을 담는다.
         * 5. AlbumVO 는 앨범 단위로 구분되어있다.
         *   -> byte 배열에 inputStream 담는다. is.available() 현재 읽을 수 있는 바이트수 반환
         *   -> new String () 으로 byte 배열, UTF-8 으로 String 생성
         *   -> new JsonObject (String) 하여 jsonObject 로 변환한다.
         *   -> album 이라는 key 를 이용해 데이터를 jsonArray 형태로 변환한다.
         * 여기서 각각의 정보에대한 name 값을 통해 각 데이터에 접근하여
         * 각각의 데이터들을 AlbumVO 에 담아야한다.
         * 여기서 Gson 라이브러리를 사용하면 한번에 해결된다.
         * 6. jsonArray while 문 생성
         * 7. AlbumVO -> testGson.fromJson(jsonArray.. )
         *    ArrayList<AlbumVO>  add(AlbumVO)
         *    while 문을 돌면서 Gson 객체가 자동으로
         *    AlbumVO.class 에 맞게 Deserialization 한다.
         * 8. String albumList = testGson.toJson(list_album);
         *    AlbumVO 에 담긴 JSON 을 javaObject 로 serialization 하여 출력한다.
         * */

        // 1. AlbumVO 를 담는 ArrayList 생성
        ArrayList<AlbumVO> list_album = new ArrayList<>();
        // 2. Gson 객체 생성
        Gson testGson = new Gson();

        // 3. try catch
        try {
            // 4. InputStream 생성하고 album.json 의 내용을 담는다.
            InputStream is = getAssets().open("album.json");
            // 5-1. byte 배열에 inputStream 담는다. is.available() 현재 읽을 수 있는 바이트수 반환
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            // 5-2. new String () 으로 byte 배열, UTF-8 으로 String 생성
            String json = new String(buffer, "UTF-8");
            // 5-3. new JsonObject (String) 하여 jsonObject 로 변환한다.
            JSONObject jsonObject = new JSONObject(json);
            // 5-4. album 이라는 key 를 이용해 데이터를 jsonArray 형태로 변환한다.
            JSONArray jsonArray = jsonObject.getJSONArray("album");

            // 6. jsonArray while 문 생성
            int index = 0;
            while (index < jsonArray.length()) {
                /*
                 * 7. AlbumVO -> testGson.fromJson(jsonArray.. )
                 *    ArrayList<AlbumVO>  add(AlbumVO)
                 *    while 문을 돌면서 Gson 객체가 자동으로
                 *    AlbumVO.class 에 맞게 Deserialization 한다.
                 * */
                AlbumVO albumVO = testGson.fromJson(jsonArray.get(index).toString(), AlbumVO.class);
                list_album.add(albumVO);
                index++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        /*
         * 8. String albumList = testGson.toJson(list_album);
         *    AlbumVO 에 담긴 JSON 을 javaObject 로 serialization 하여 출력한다.
         * */
        String albumList = testGson.toJson(list_album);
        Log.e("albumList",albumList);


        imageView_test = findViewById(R.id.imageView_test);
        imageView_bitmap = findViewById(R.id.imageView_bitmap);


        /**
         * Glide 를 사용하여 URL 로부터 이미지를 로딩하여 이미지 뷰에 출력하는 예제이다.
         **/
        Glide.with(this) // context
                .load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_160x56dp.png") // 이미지 url
                .into(imageView_test); // 붙일 imageView

        /*
         * Glide 를 사용하여 Url 로 이미지를 로딩하고 bitmap 으로 변환하여 출력하는 예제이다.
         * */
        Glide.with(this) // context
                .asBitmap()
                .load("https://images.ctfassets.net/1khq4uysbvty/5OX9R2XaEgk0Eo04cC8cWA/ad2741c623bf45cb6aa77d9192d9a1d0/bitmap.png")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        Log.e("bitmap", "비트맵변환한거0 => " + resource);
                        imageView_bitmap.setImageBitmap(resource);
                        return false;
                    }
                }).submit();

    }
}
