package com.example.sqliteexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAddTask) {
            // Open dialog for adding task
            openAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAddDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.add_dialog);
        EditText edtName = dialog.findViewById(R.id.edtTaskName);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Insert data
                String taskName = edtName.getText().toString();
                if (taskName.equals("")) {
                    Toast.makeText(MainActivity.this, "Please type your task name.", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.execSql("INSERT INTO " + MyDatabaseHelper.TBL_NAME + " VALUES(null, '" + taskName + "')");
                    Toast.makeText(MainActivity.this, "Insert successfully", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialog.dismiss();
                }
            }
        });
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void openEditDialog(Task t) {
//        Toast.makeText(this, t.getTaskName(), Toast.LENGTH_SHORT).show();
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_dialog);
        EditText edtTaskName = dialog.findViewById(R.id.edtTaskName);
        edtTaskName.setText(t.getTaskName());

        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = edtTaskName.getText().toString();
                db.execSql("UPDATE " + MyDatabaseHelper.TBL_NAME + " SET " + MyDatabaseHelper.COL_TASK_NAME + " = '" + taskName + "' WHERE " + MyDatabaseHelper.COL_TASK_ID + " = " + t.getTaskId());

                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                loadData();

                dialog.dismiss();
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void deleteTask(Task t) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("u sure to delete?");
        builder.setPositiveButton("Yes!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.execSql("DELETE FROM " + MyDatabaseHelper.TBL_NAME + " WHERE " + MyDatabaseHelper.COL_TASK_ID + " = " + t.getTaskId());
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();

    }
}