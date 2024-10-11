package com.example.todowatanabeapplication.dto;

import androidx.annotation.OpenForTesting;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "todo_table")
public class ToDoItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private boolean status;
    //private LocalDate deadLine;
    private String deadLine;
    private String toDoTitle;
    private String toDoDetail;

    private String priority;


    public ToDoItem(boolean status, String deadLine , String toDoTitle, String toDoDetail, String priority){
        this.status = status;
        this.deadLine = deadLine;
        this.toDoTitle = toDoTitle;
        this.toDoDetail = toDoDetail;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){ return id; }

    public boolean getStatus(){
        return status;
    }

    public String getDeadLine(){
        return deadLine;
    }

    public String getToDoTitle(){
        return toDoTitle;
    }

    public String getToDoDetail(){ return toDoDetail;}

    public String getPriority(){ return priority;}


}

