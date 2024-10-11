package com.example.todowatanabeapplication;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todowatanabeapplication.dto.ToDoItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoDatabase db;
    //private toDoDao toDoDao;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ToDoItem> todoList;  // ToDoデータリスト
    private ImageButton createToDoButton;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa","bbb");
        setContentView(R.layout.activity_main);


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
                        myAdapter = new MyAdapter(todoList);
                        recyclerView.setAdapter(myAdapter);
                    }
                });
            }
        }).start();


//        todoList = new ArrayList<>();
//        todoList.add(new ToDoItem(false, "10/10","買い物", "たまご買う", "低"));
//        todoList.add(new ToDoItem(true,"10/17","会議", "デイリー","高"));
//        todoList.add(new ToDoItem(false, "10/9","運動","1時間のランニング","中"));
//
//        myAdapter = new MyAdapter(todoList);
//        recyclerView.setAdapter(myAdapter);


        // 新規タスク登録ボタンの設定
        createToDoButton = findViewById(R.id.createToDoButton);
        createToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CreateTodoActivityへ画面遷移
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }

        })
    ;}
}
