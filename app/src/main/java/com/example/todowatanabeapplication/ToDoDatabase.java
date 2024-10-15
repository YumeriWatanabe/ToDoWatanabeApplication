package com.example.todowatanabeapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todowatanabeapplication.database.dao.toDoDao;
import com.example.todowatanabeapplication.database.dao.dto.ToDoItem;

@Database(entities = {ToDoItem.class}, version = 1, exportSchema = false)
//@TypeConverters(converter.class)  // Convertersクラスを登録
public abstract class ToDoDatabase extends RoomDatabase {
    public abstract toDoDao toDoDao();
}
