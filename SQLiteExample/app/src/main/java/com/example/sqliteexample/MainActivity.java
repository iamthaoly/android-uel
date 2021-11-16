package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDBFromAsset();
        processCopyDB();
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