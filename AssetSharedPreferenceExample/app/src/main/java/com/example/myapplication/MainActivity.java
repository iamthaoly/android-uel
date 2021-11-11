package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ListView lvFonts;
    TextView txtText;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linksViews();
        loadFontList();
    }

    private void linksViews() {
        lvFonts = findViewById(R.id.lvFonts);
        txtText = findViewById(R.id.txtText);
    }

    private void loadFontList() {
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        lvFonts.setAdapter(adapter);

        AssetManager manager = getAssets();
        try {
            String[] fontArr = manager.list("fonts");
            adapter.addAll(fontArr);
        } catch (IOException e) {
            Log.e("Error: ", e.toString());
        }
    }


}