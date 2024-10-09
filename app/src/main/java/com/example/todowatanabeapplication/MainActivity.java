package com.example.todowatanabeapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowatanabeapplication.dto.todoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<todoItem> todoList;  // ToDoデータリスト

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerViewの初期化
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoList = new ArrayList<>();
        todoList.add(new todoItem(false, "10/10","買い物", 2));
        todoList.add(new todoItem(true,"10/17","会議", 3));
        todoList.add(new todoItem(false, "10/9","1時間ランニング",1));

        myAdapter = new MyAdapter(todoList);
        recyclerView.setAdapter(myAdapter);

        };
    }
