package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    Button btnSave, btnLoad;
    TextView txtResult;

    public static final String PREFERENCE_NAME = "my_data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("numb" , 8);
                editor.putFloat("score", 8.2f);
                editor.putString("text", "A whole new string");

                Set<String> set = new HashSet<>();
                set.add("android");
                set.add("web");
                set.add("macOS");

                editor.putStringSet("set", set);
                editor.apply();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                int number = preferences.getInt("numb", 0);
                float score = preferences.getFloat("score", 0.0f);
                String text = preferences.getString("text", "");

                Set<String> set =  preferences.getStringSet("set", null);
                if (set != null) {

                }
                txtResult.setText(text);
            }
        });
    }

    private void linkViews() {
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        txtResult = findViewById(R.id.txtContent);
    }
}