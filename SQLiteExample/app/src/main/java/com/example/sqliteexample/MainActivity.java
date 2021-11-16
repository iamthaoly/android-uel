package com.example.sqliteexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadData();
//        copyDBFromAsset();
        processCopyDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAdd) {
            // Open new screen
            Intent intent = new Intent(MainActivity.this, AddProduct.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        adapter = new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1, initData());
        lvProduct.setAdapter(adapter);
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();
//        products.add(new Product(1, "TIgurr", 19.22));
//        products.add(new Product(1, "Big cat", 12));
//        products.add(new Product(1, "Jungle king", 13));

        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Product", null);
        int id;
        String name;
        double price;
        Product p;
        while(cursor.moveToNext()) {
            id = cursor.getInt(0);
            name = cursor.getString(1);
            price = cursor.getDouble(2);
            p = new Product(id, name, price);
            products.add(p);
        }
        cursor.close();
        return products;
    }

    private void linkViews() {
        lvProduct = findViewById(R.id.lvProduct);
    }

    private void processCopyDB() {
        File dbFile = getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            if (copyDBFromAsset()) {
                // Show successful toast
            }
            else {
                // Error toast
            }
        }
    }



    private boolean copyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DB_NAME;
        File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX) ;
        if (!f.exists()) {
            if (!f.mkdir()) {
                return false;
            }
        }

        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}