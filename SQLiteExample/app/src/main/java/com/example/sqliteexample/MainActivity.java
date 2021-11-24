package com.example.sqliteexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.Product;
import com.example.utils.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;
    public static SQLiteDatabase db;

    Product selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadData();
//        copyDBFromAsset();
        addEvents();
        processCopyDB();

        registerForContextMenu(lvProduct);
    }

    private void addEvents() {
        lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = adapter.getItem(i);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Listview must reload after other activity add data.
        loadData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnEdit) {
            Intent intent = new Intent(MainActivity.this, EditProduct.class);
            // Attach data
            intent.putExtra(Constant.SELECTED_ITEM, selectedProduct);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.mnDelete) {
            if (selectedProduct != null) {
                // Confirm before delete
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you want to delete this product?");
                builder.setIcon(R.drawable.ic_launcher_background);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int flag = db.delete("Product", "ProductId=?", new String[] {selectedProduct.getProductId() + ""});
                        if (flag > 0) {
                            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                            loadData();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                        }
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

        return super.onContextItemSelected(item);
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