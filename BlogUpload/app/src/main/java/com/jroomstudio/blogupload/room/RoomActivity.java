package com.jroomstudio.blogupload.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jroomstudio.blogupload.R;

public class RoomActivity extends AppCompatActivity {

    //UI View
    private EditText mAddEdit, mUpdateIdEdit, mUpdateTitleEdit, mDeleteEdit;
    private TextView mResultTextView;
    private Button mAddButton, mUpdateButton, mDeleteButton, mClearButton;

    // Room Database
    private LocalDatabase db;

    private final String INSERT = "INSERT";
    private final String UPDATE = "UPDATE";
    private final String DELETE = "DELETE";
    private final String CLEAR = "CLEAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        mAddEdit = (EditText) findViewById(R.id.add_edit);
        mUpdateIdEdit = (EditText) findViewById(R.id.update_id_edit);
        mUpdateTitleEdit = (EditText) findViewById(R.id.update_title_edit);
        mDeleteEdit = (EditText) findViewById(R.id.delete_edit);

        mResultTextView = (TextView) findViewById(R.id.result_text);
        mAddButton = (Button) findViewById(R.id.add_button);
        mUpdateButton = (Button) findViewById(R.id.update_button);
        mDeleteButton = (Button) findViewById(R.id.delete_button);
        mClearButton = (Button) findViewById(R.id.clear_button);

        /**
         * LocalDatabase 객체 생성 -> db
         * databaseBuilder(context, Database 객체, Database 명)
         * Database 명의 파일이 실제로 생성 된다.
         **/
        db = Room.databaseBuilder(this, LocalDatabase.class, "test-db")
                .build();

        /**
         * Database 를 관찰하고 변경이 감지될 때 UI 갱신
         * 데이터베이스 db -> 데이터베이스 mDataModelDAO()
         * -> getAll() 가져오는 List<DataModel> 객체는 관찰 가능한 객체 이므로
         * -> observe 메소드로 관찰하고 변경이 되면 dataList 에 추가한다.
         * 변경된 내용이 담긴 dataList 를 출력한다.
         **/
        /*
        * 람다식 사용
        * file -> project structure -> modules -> source compatibility, target compatibility -> 1.8
        **/
        db.dataModelDAO().getAll().observe(this, dataList -> {
            mResultTextView.setText(dataList.toString());
        });

        /**
         * Insert
         * 데이터배이스 객체 . 데이터베이스 DAO . insert -> new DataModel (텍스트 추가)
         **/
        mAddButton.setOnClickListener(v -> {
            /**
             *  AsyncTask 생성자에 execute 로 DataModelDAO 객체를 던저준다.
             *  비동기 처리
             **/
            new DaoAsyncTask(db.dataModelDAO(),INSERT,0,"")
                    .execute(new DataModel(mAddEdit.getText().toString()));
        });

        /**
         * Update
         * 데이터베이스 -> getData(id) -> id 입력하여 DataModel 받아오기
         * -> update(DataModel) 해당 데이터 업데이트
         **/
        mUpdateButton.setOnClickListener(v ->
                        new DaoAsyncTask(
                                db.dataModelDAO(),
                                UPDATE,
                                Integer.parseInt(mUpdateIdEdit.getText().toString()),
                                mUpdateTitleEdit.getText().toString()
                        ).execute()
        );

        /**
         * Delete
         * 데이터베이스 -> getData(id) -> id 입력하여 DataModel 받아오기
         * -> delete(DataModel) 해당 데이터 삭제
         * */
        mDeleteButton.setOnClickListener(v ->
                new DaoAsyncTask(
                        db.dataModelDAO(),
                        DELETE,
                        Integer.parseInt(mDeleteEdit.getText().toString()),
                        ""
                ).execute()
        );


        /**
         * Clear
         * 데이터베이스 -> allClear -> 리스트 전부 지움
         * */
        mClearButton.setOnClickListener(v ->
                new DaoAsyncTask(db.dataModelDAO(),CLEAR,0,"").execute()
        );


    }

    /**
     * 비동기 처리 해주는 것은 dataModelDAO() 이다.
     * InsertAsyncTask 생성자를 만들어 dataModelDAO() 객체를 받는다.
     **/
    private static class DaoAsyncTask extends AsyncTask<DataModel,Void,Void>{


        private DataModelDAO mDataModelDAO;
        private String mType;
        private int mId;
        private String mTitle;


        private DaoAsyncTask (DataModelDAO dataModelDAO, String type, int id, String title) {
            this.mDataModelDAO = dataModelDAO;
            this.mType = type;
            this.mId = id;
            this.mTitle = title;
        }

        @Override
        protected Void doInBackground(DataModel... dataModels) {

            if(mType.equals("INSERT")){
                mDataModelDAO.insert(dataModels[0]);
            }
            else if(mType.equals("UPDATE")){
                if(mDataModelDAO.getData(mId) != null){
                    mDataModelDAO.dataUpdate(mId,mTitle);
                }
            }
            else if(mType.equals("DELETE")){
                if(mDataModelDAO.getData(mId) != null) {
                    mDataModelDAO.delete(mDataModelDAO.getData(mId));
                }
            }
            else if(mType.equals("CLEAR")){
                mDataModelDAO.clearAll();
            }
            return null;
        }

    }

}
