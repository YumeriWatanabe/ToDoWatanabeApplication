package com.example.todowatanabeapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todowatanabeapplication.dto.ToDoItem;

import java.util.List;

@Dao
public interface toDoDao{
        //タスクをデータベースに追加するメソッド
        @Insert
        void insert(ToDoItem toDoItem);

        @Delete
        void delete(ToDoItem toDoItem);

        @Query("Select * From todo_table")
        List<ToDoItem> getAllToDoItems();
    }


