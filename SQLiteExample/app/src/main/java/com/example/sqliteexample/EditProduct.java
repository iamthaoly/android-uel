package com.example.sqliteexample;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Product;
import com.example.utils.Constant;

public class EditProduct extends AppCompatActivity {

    EditText edtName, edtPrice;
    Button btnOK, btnCancel;

    public static final String TABLE_NAME = "Product";

    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        linkViews();
        addEvents();

        getData();
    }

    private void getData() {
        p = (Product) getIntent().getSerializableExtra(Constant.SELECTED_ITEM);
        edtName.setText(p.getProductName());
        edtPrice.setText(String.valueOf( p.getProductPrice()));
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
                long flag = MainActivity.db.update(Constant.TABLE_NAME, values,"ProductId = ?",
                        new String[]{String.valueOf(p.getProductId())});
                if (flag > 0) {
                    Toast.makeText(EditProduct.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProduct.this, "Fail!", Toast.LENGTH_SHORT).show();
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