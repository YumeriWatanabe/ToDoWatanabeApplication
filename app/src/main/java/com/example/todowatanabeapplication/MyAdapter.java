package com.example.todowatanabeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> itemList;

    // コンストラクタでデータリストを受け取る
    public MyAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    // ビューホルダーの定義
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    // レイアウトをビューホルダーにバインド
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    // データをバインド
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.textView.setText(item);
    }

    // リストのサイズを返す
    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
