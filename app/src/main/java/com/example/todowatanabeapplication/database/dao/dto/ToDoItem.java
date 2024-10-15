package com.example.todowatanabeapplication.database.dao.dto;

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

    public void setStatus(boolean status){
        this.status = status;
    }

    public String getDeadLine(){
        return deadLine;
    }

    public void setDeadLine(String deadLine){
        this.deadLine = deadLine;
    }

    public String getToDoTitle(){
        return toDoTitle;
    }

    public void setToDoTitle(String toDoTitle){
        this.toDoTitle = toDoTitle;
    }

    public String getToDoDetail(){ return toDoDetail;}

    public void setToDoDetail(String toDoDetail){
        this.toDoDetail = toDoDetail;
    }

    public String getPriority(){ return priority;}

    public void setPriority(String priority){
        this.priority = priority;
    }

}

