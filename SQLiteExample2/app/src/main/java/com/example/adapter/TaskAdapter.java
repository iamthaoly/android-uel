package com.example.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Task;
import com.example.sqliteexample2.R;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    Context context;
    int item_layout;
    List<Task> tasks;

    public TaskAdapter(Context context, int item_layout, List<Task> tasks) {
        this.context = context;
        this.item_layout = item_layout;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtName = view.findViewById(R.id.txtTaskName);
            holder.imvDelete = view.findViewById(R.id.imvDelete);
            holder.imvEdit= view.findViewById(R.id.imvEdit);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        // Binding
        Task t = tasks.get(i);
        // Set text later
        holder.txtName.setText(t.getTaskName());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private static class ViewHolder {
        TextView txtName;
        ImageView imvEdit, imvDelete;
    }
}
