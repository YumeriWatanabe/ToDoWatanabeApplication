package com.example.todowatanabeapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todowatanabeapplication.database.dao.dto.ToDoItem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ToDoDatabase db;
    private static List<ToDoItem> todoItems;
    private Context context;
    private Activity activity;

    // コンストラクタでデータリストを受け取る
    public MyAdapter(List<ToDoItem> todoItems, Context context, Activity activity) {
        this.todoItems = todoItems;
        this.context = context;
        this.activity = activity;
    }

    // ビューホルダーの定義
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView dueDate;
        public TextView taskTitle;
        public TextView priority;
        public Button detailButton;
        //public ImageButton editButton;
        //public ImageButton deleteButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            dueDate = itemView.findViewById(R.id.dueDate);
            taskTitle = itemView.findViewById(R.id.taskTitle);
            priority = itemView.findViewById(R.id.priority);
            detailButton = itemView.findViewById(R.id.detailButton);
            //editButton = itemView.findViewById(R.id.editImageButton);
            //deleteButton = itemView.findViewById(R.id.deleteImageButton);
        }
    }

    // レイアウトをビューホルダーにバインド
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new MyViewHolder(view);
    }

    // データをバインド
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        db = Room.databaseBuilder(context, ToDoDatabase.class, "todo_database").build();

        ToDoItem todoItem = todoItems.get(position);
        holder.checkBox.setChecked(todoItem.getStatus());
        holder.dueDate.setText(todoItem.getDeadLine());
        holder.taskTitle.setText(todoItem.getToDoTitle());
        //holder.priority.setText(String.valueOf(todoItem.getPriority()));

        String priority = todoItem.getPriority();
        if (priority.equals("高")) {
            holder.priority.setText("重要度: 高");
            holder.priority.setTextColor(Color.RED);
        } else if (priority.equals("中")) {
            holder.priority.setText("重要度: 中");
            holder.priority.setTextColor(Color.BLUE);
        } else {
            holder.priority.setText("重要度: 低");
            holder.priority.setTextColor(Color.GREEN);
        }

        holder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context,EditActivity.class);
                intent.putExtra("TODO_ID", todoItem.getId());
                activity.startActivity(intent);
                //activity.startActivityForResult(intent, MainActivity.REQUEST_CODE_EDIT_TODO);
            }
        });

//        holder.editButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(context,EditActivity.class);
//                intent.putExtra("TODO_ID", todoItem.getId());
//                activity.startActivity(intent);
//                //activity.startActivityForResult(intent, MainActivity.REQUEST_CODE_EDIT_TODO);
//            }
//        });

//        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                new Thread(new Runnable() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void run() {
//                        db.toDoDao().delete(todoItem);
//                        //notifyItemRemoved(position);
//                        //notifyDataSetChanged();
//                        //activity.();
//                    }
//                }).start();
//            }
//        });
    }
    // リストのサイズを返す
    @Override
    public int getItemCount() {
        return todoItems.size();
    }

}
