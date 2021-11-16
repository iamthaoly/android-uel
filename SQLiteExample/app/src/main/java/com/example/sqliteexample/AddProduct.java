package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

    EditText edtName, edtPrice;
    Button btnOK, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Insert data
                String name = edtName.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());
                ContentValues values = new ContentValues();
                values.put("ProductName", name );
                values.put("ProductPrice", price);
                long flag = MainActivity.db.insert("Product", null, values);
                if (flag > 0) {
                    Toast.makeText(AddProduct.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddProduct.this, "Fail!", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void linkViews() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCanel);
    }
}