package com.jroomstudio.room_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        /**
         * Room Database 객체 생성
         * databaseBuilder(context, Database 객체, Database 명)
         * Database 명 의 파일이 실제로 생성 된다.
         **/

        final AppDatabase db =
                Room.databaseBuilder(this,AppDatabase.class,"todo-db")
                        //.allowMainThreadQueries() // 임시 - Test 용
                        .build();

        /**
         * Database 를 관찰하고 변경이 감지될 때 UI 갱신
         * 데이터베이스 db -> 데이터베이스 DAT todoDAO()
         * -> getAll() 가져오는 Todo<List> 객체는 관찰 가능한 객체 이므로
         * -> observe 메소드로 관찰하고 변경이 되면 todos 에 추가한다.
         *
         * 변경된 내용이 담긴 todos 를 출력한다.
         **/
        db.todoDAO().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        //mResultTextView.setText(db.todoDAO().getAll().toString());

        /**
         * 버튼 클릭 시 DB 에 Insert
         * 데이터배이스 객체 . 데이터베이스 DAO . insert -> new Todo (텍스트 추가)
         **/
        findViewById(R.id.add_button).setOnClickListener(v -> {

            /**
             *  AsyncTask 생성자에 execute 로 TodoDAO 객체를 던저준다.
             *  비동기 처리
             **/
            new InsertAsyncTask(db.todoDAO())
                    .execute(new Todo(mTodoEditText.getText().toString()));
            //db.todoDAO().insert(new Todo(mTodoEditText.getText().toString()));
            //mResultTextView.setText(db.todoDAO().getAll().toString());
        });
    }


    private static class InsertAsyncTask extends AsyncTask<Todo,Void,Void>{
        /**
         * insert 메소드를 비동기 처리 해주는 것은 todoDAO() 이다.
         * InsertAsyncTask 생성자를 만들어 TodoDAO 객체를 받는다.
         **/
        private TodoDAO mTodoDAO;

        private InsertAsyncTask(TodoDAO mTodoDAO) {
            this.mTodoDAO = mTodoDAO;
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param todos The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Todo... todos) {
            // todos 라는 배열로 넘어오고 1개만 출력한다.
            mTodoDAO.insert(todos[0]);
            return null;
        }
    }

}
