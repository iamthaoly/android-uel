package com.example.sqliteexample2;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.SQLData;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "task_db.sqlite";
    public static final String TBL_NAME = "Task";
    public static final String COL_TASK_ID =  "Task_Id";
    public static final String COL_TASK_NAME =  "Task_Name";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create table
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TASK_NAME + " VARCHAR(200))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(sqLiteDatabase);
    }

    public int getCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        int count = cursor.getCount();
        cursor.close();


        return count;
    }

    public void createSomeDefaultTask() {
        int count  = getCount();
        if (count == 0) {
            //
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Coding')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Baking')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Coding')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Coding')");
        }
    }

    public void execSql(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    // Select
    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
}
