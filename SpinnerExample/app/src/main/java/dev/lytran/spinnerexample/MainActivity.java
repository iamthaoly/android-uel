package dev.lytran.spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spProvince;
    ArrayList<String> provinces;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadData();
    }


    private void linkViews() {
        spProvince = findViewById(R.id.spProvinces);
    }

    private void loadData() {
        provinces = new ArrayList<>();
        provinces.add("TP. HCM");
        provinces.add("Ha Noi");
        provinces.add("Da Nang");
        provinces.add("Dong Nai");
        provinces.add("Ca Mau");

//        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, provinces);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_checked, provinces);
        spProvince.setAdapter(adapter);
    }
}