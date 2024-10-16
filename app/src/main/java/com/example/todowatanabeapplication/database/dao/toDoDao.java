package com.example.todowatanabeapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todowatanabeapplication.database.dao.dto.ToDoItem;

import java.util.List;

@Dao
public interface toDoDao{
        //タスクをデータベースに追加するメソッド
        @Insert
        void insert(ToDoItem toDoItem);

        @Update
        void update(ToDoItem toDoItem);

        @Delete
        void delete(ToDoItem toDoItem);

        @Query("Select * From todo_table where id = :toDoId")
        ToDoItem getToDoItemById (int toDoId);

        @Query("Select * From todo_table where status = 1")
        List <ToDoItem> getCompletedToDoItems();

        @Query("Select * From todo_table where status = 0")
        List <ToDoItem> getInCompletedToDoItems();

        @Query("Select * From todo_table")
        List<ToDoItem> getAllToDoItems();
    }


