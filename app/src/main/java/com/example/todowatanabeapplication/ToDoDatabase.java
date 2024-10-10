package com.example.todowatanabeapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todowatanabeapplication.dao.toDoDao;
import com.example.todowatanabeapplication.dto.ToDoItem;

@Database(entities = {ToDoItem.class}, version = 1, exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase {
    public abstract toDoDao toDoDao();
}
