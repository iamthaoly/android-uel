package com.example.sqliteexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.ServiceAdapter;
import com.example.model.Service;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lvServices;
    ServiceAdapter adapter;
    ArrayList<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linkViews();
        loadData();
    }

    private void loadData() {
        services = new ArrayList<>();
        Cursor cursor = MainActivity.db.getData("SELECT * FROM " + MyDatabase.TBL_NAME);
        while (cursor.moveToNext()) {
            services.add(new Service(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3)));
        }
        cursor.close();
        adapter = new ServiceAdapter(MainActivity2.this, R.layout.item_list, services);
        lvServices.setAdapter(adapter);
    }

    private void linkViews() {
        lvServices = findViewById(R.id.lvServices);
    }
}