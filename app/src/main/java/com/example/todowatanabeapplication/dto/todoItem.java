package com.example.todowatanabeapplication.dto;

import java.time.LocalDate;

public class todoItem {
    private boolean status;
    private String deadLine;
    private String taskTitle;
    private int priority;


    public todoItem(boolean status, String deadLine , String taskTitle, int priority){
        this.status = status;
        this.deadLine = deadLine;
        this.taskTitle = taskTitle;
        this.priority = priority;
    }

    public boolean getStatus(){
        return status;
    }

    public String getDeadLine(){
        return deadLine;
    }

    public String getTaskTitle(){
        return taskTitle;
    }

    public int getPriority(){
        return priority;
    }


}

