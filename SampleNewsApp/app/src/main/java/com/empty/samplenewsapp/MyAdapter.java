package com.empty.samplenewsapp;

import android.content.Context;
import android.net.Uri;
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

// 해당 클래스는 리사이클러뷰의 Adapter<ViewHolder> 를 상속받느다.
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // 리사이클러뷰는 리스트 형태로 view 를 관리하며
    // 각각 의 뷰에 titleText, descriptionText, Image 등을 셋팅할 수 있고
    // 각각 나뉘어진 뷰에 정보를 구분하여 셋팅할 수 있는 기능을 제공한다.

    // 셋팅되는 정보인 title, description, imageUrl 정보를 담고있는 NewsData 객체를
    // List<NewsData> 에 넣어 관리한다.
    private List<NewsData> mDataset;
    // 각각의 View 를 클릭하여 MainActivity 에서 NewsPageActivity 로 이동하는 기능이 구현되어있다.
    // 클릭을 해야하기 때문에 onclickListener 가 필요하다 .
    private static View.OnClickListener onClickListener;

    // onCreate 안에 R.id.~~ 를 사용하여 연결된 activity.xml 에 셋팅되어있는
    // View 를 연결해주는 기능을 담당한다.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // onCreate 에서와 동일하게 각각의 뷰를 생성한다.
        public TextView TextView_title;
        public TextView TextView_description;
        public ImageView ImageView_title;


        public View rootView;

        // RecyclerView 안에 있는 1개의 뷰 즉, row_news.xml 에 접근한다.
        // View 를 입력값으로 받는다.
        // OnCreateViewHolder 에서 view 생성시 해당 메소드를 호출하여 각각의 View 를 담아준다.
        public MyViewHolder(View v) {
            super(v);
            // v -> row_news.xml
            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_description = v.findViewById(R.id.TextView_description);
            ImageView_title = (SimpleDraweeView) v.findViewById(R.id.ImageView_title);

            // View rootView 를 만들고  그안에 생성된 View 를 셋팅한다.
            // 이제 각각의 view 를 tag 를 통해 구분지어 접근할 수 있다.
            rootView = v;

            // v 는 LinearLayout 으로 되어있으므로 클릭 기능이 없다.
            // 다음과 같이 클릭 기능을 추가하고
            v.setClickable(true); // 누를 수 있다없다
            v.setEnabled(true); // 활성화,   비활성화
            // 클릭 감지를 위해 onClickListener 도 추가한다.
            v.setOnClickListener(onClickListener);
        }
    }

    // Adapter 의 생성자이다.
    // 생성시 List<NewsData> 와 해당 액티비티의 context, 그리고 온클릭 리스너를 받아 셋팅한다.
    public MyAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick) {
        mDataset = myDataset;
        onClickListener = onClick;

        // 프레스코 -> 이미지를 표시하기 위한 시스템
        // 이미지 로딩 및 디스플레이를 관리한다.
        // 네트워크, 로컬저장소, 로컬리소스에서 이미지를 불러온다.
        // 애플리케이션에 이미지를 표시하기 위해서는 다소 복잡한 과정을 거쳐야 하는데
        // 그 복잡한 부분을 Fresco 를 통해서 간단하게 구현할 수 있다.
        // 비슷한 라이브러리로 glide 가 있다.
        // https://github.com/facebook/fresco

        //1.  프레스코 초기화
        // 프레스코 사용을 위해 인터넷 권한을 추가해야한다.
        // 매니패스트 -> 퍼미션 -> 인터넷
        Fresco.initialize(context);
    }

    // View 즉 row_news.xml -> 을 생성해 주는 부분이다.
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // LinearLayout 으로 되어있기 때문에 LinearLayout 에 생성된 VIew 를 담는다.
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        // MyViewHolder 에 생성된 View 를 넣어준다.
        MyViewHolder vh = new MyViewHolder(v);
        return vh; // 끝으로 생성된 view 가 반환된다.
    }

    // 각각의 view 에 정보를 셋팅해주는 부분이다.
    // int position 으로 각각의 정보가 구분되어진다.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // List<NewsData> 안에 position 값으로 접근하여 NewsData 객체를 찾는다.
        NewsData news = mDataset.get(position);

        // holder 는 각각의 row_news.xml View 이다.

        // 해당 row_news.xml 에서 TextView_title 에  현재 NewsData 객체에 셋팅된 정보를 가져와 셋팅한다.
        holder.TextView_title.setText(news.getTitle());


        // TextView_description  셋팅
        String des = news.getDescription();
        // description 같은 경우 null 이라는 문자가 들어있는 경우가 있어서
        // 다음과 같이 null 일경우 "-" 을 담도록 하였다.
        if(!des.equals("null")){
            holder.TextView_description.setText(des);
        }else{
            holder.TextView_description.setText("-");
        }

        //ImageView_title 셋팅
        // 이미지를 URI 로 접근하여 보여주는 방식인 .setImageURI 는
        // Uri 객체를 입력값으로 받는다.
        // Uri 는 프레스코에서 지원하는 기능이다. 
        // NewsData 안에 imageUrl 을 Uri 객체에 담고
        Uri uri = Uri.parse(news.getUrlToImage());
        // .setImageURI( ) 안에 uri 를 담는다.
        holder.ImageView_title.setImageURI(uri);

        // tag - label
        // rootView 는 row_news.xml 이 생성된 각각의 view 이다.
        // rootView 에 setTag 에 onBindViewHolder 의 구분자인 position 을 셋팅함으로써
        // 이제 이 안에서 뿐만아니라 외부에서도 List<NewsData> 에 position 으로 접근하여
        // 각각의 뷰를 구분할 수 있게 되었다.
        holder.rootView.setTag(position);
    }


    // 현재 아이템이 몇개 있는지를 반환해주는 메소드이다.
    // List<> 이므로 .size() 로 아이템의 총 갯수를 알려준다.
    @Override
    public int getItemCount() {
        //삼항 연산자
        return mDataset == null ? 0 : mDataset.size();
    }

    // 외부에서 List<NewsData> 에 position 값으로 접근하여 해당 객체를 반환해주는 메소드이다.
    // position 은 각각의 View 에 setTag 로 입력되어있으므로
    // getTag 를 통하여 position 값을 얻을 수 있다.
    public NewsData getNews(int position) {
         return mDataset != null ? mDataset.get(position) : null;
    }

}