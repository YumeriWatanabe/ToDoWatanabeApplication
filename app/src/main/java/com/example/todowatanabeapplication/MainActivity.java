package com.example.todowatanabeapplication;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todowatanabeapplication.database.dao.dto.ToDoItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoDatabase db;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ToDoItem> todoList;  // すべてのToDoデータリスト
    private List<ToDoItem> completedToDoList;
    private List<ToDoItem> inCompletedToDoList;
    private ImageButton createToDoButton;
    private Switch viewSwitch;
    private TextView listTitle;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("aaa","bbb");
        setContentView(R.layout.activity_main);

        listTitle = findViewById(R.id.todolist);

        // データベースインスタンスを作成
        db = Room.databaseBuilder(getApplicationContext(),
                ToDoDatabase.class, "todo_database").build();

        // RecyclerViewの初期化
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                todoList = db.toDoDao().getAllToDoItems();  // DAO経由でタスク一覧を取得
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // アダプタをRecyclerViewにセット
                        myAdapter = new MyAdapter(completedToDoList, getApplicationContext(), MainActivity.this);//MainActivity.this
                        recyclerView.setAdapter(myAdapter);
                    }
                });
            }
        }).start();

        //ビュー切り替え
        viewSwitch = findViewById(R.id.switching_View);
        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                                      //onのときは完了済みのtodoリストを表示、offのときは未完了のtodoリストを表示
                                                      if (isChecked) {
                                                          new Thread(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  listTitle.setText(R.string.completed_todolist);
                                                                  completedToDoList = db.toDoDao().getCompletedToDoItems();  // DAO経由で完了タスク一覧を取得
                                                                  runOnUiThread(new Runnable() {
                                                                      @Override
                                                                      public void run() {
                                                                          // アダプタをRecyclerViewにセット
                                                                          myAdapter = new MyAdapter(completedToDoList, getApplicationContext(), MainActivity.this);//MainActivity.this
                                                                          recyclerView.setAdapter(myAdapter);
                                                                      }
                                                                  });
                                                              }
                                                          }).start();

                                                      } else {
                                                          new Thread(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  listTitle.setText(R.string.inCompleted_todolist);
                                                                  inCompletedToDoList = db.toDoDao().getInCompletedToDoItems();  // DAO経由で未完了タスク一覧を取得
                                                                  runOnUiThread(new Runnable() {
                                                                      @Override
                                                                      public void run() {
                                                                          // アダプタをRecyclerViewにセット
                                                                          myAdapter = new MyAdapter(inCompletedToDoList, getApplicationContext(), MainActivity.this);//MainActivity.this
                                                                          recyclerView.setAdapter(myAdapter);
                                                                      }
                                                                  });
                                                              }
                                                          }).start();
                                                      }
                                                  }
                                              });

        // 新規タスク登録ボタンの設定
        createToDoButton = findViewById(R.id.createToDoButton);
        createToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CreateTodoActivityへ画面遷移
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                listTitle.setText(R.string.todolist);
                todoList = db.toDoDao().getAllToDoItems(); // タスク一覧を取得
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // RecyclerViewにデータを反映
                        myAdapter = new MyAdapter(todoList, getApplicationContext(), MainActivity.this);
                        recyclerView.setAdapter(myAdapter);
                    }
                });
            }
        }).start();
    }

}
