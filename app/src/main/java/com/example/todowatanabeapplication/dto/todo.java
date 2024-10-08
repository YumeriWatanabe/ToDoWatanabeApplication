package com.example.todowatanabeapplication.dto;

import java.time.LocalDate;

public class todo {
    private boolean status;
    private LocalDate dueDate;
    private String taskTitle;
    private int priority;

    public todo(boolean status, LocalDate dueDate, String taskTitle, int priority){
        this.status = status;
        this.dueDate = dueDate;
        this.taskTitle = taskTitle;
        this.priority = priority;
    }

    public boolean getStatus(){
        return status;
    }

    public LocalDate getdueDate(){
        return dueDate;
    }

    public String getTaskTitle(){
        return taskTitle;
    }

    public int getPriority(){
        return priority;
    }

}

