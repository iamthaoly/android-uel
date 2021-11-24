package com.example.sqliteexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.TaskAdapter;
import com.example.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper db;
    ListView lvTask;
    TaskAdapter adapter;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        prepareDB();
        loadData();
    }

    private void loadData() {

        adapter = new TaskAdapter(MainActivity.this, R.layout.item_list, getDataFromDB());
        lvTask.setAdapter(adapter);
    }

    public ArrayList<Task> getDataFromDB() {
        tasks = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + db.TBL_NAME);
        while (cursor.moveToNext()) {
            tasks.add(new Task(cursor.getInt(0), cursor.getString(1)));

        }
        cursor.close();
        return tasks;
    }

    private void linkViews() {
        lvTask = findViewById(R.id.lvTask);
    }

    private void prepareDB() {
        db = new MyDatabaseHelper(this);
        db.createSomeDefaultTask();
    }
}