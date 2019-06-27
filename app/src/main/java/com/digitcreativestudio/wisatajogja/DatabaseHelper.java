package com.digitcreativestudio.wisatajogja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "user_db";
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(User.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(String name, String email, String password, String address) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_FULLNAME, name);
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASSWORD, password);
        values.put(User.COLUMN_ADDRESS, address);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public boolean existCheck(String email) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID,
                        User.COLUMN_EMAIL,
                        User.COLUMN_PASSWORD,
                        User.COLUMN_FULLNAME,
                        User.COLUMN_ADDRESS,
                        User.COLUMN_TIMESTAMP},
                User.COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        boolean result = false;

        try {
            User data = new User();
            data.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));

            if(data.getEmail().equals(email)){
                result = true;
                Toast.makeText(context, "email sudah ada", Toast.LENGTH_LONG).show();
            }
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.e("ERROR", e.toString());
        }

        cursor.close();
        return result;
    }

    public boolean loginCheck(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID,
                        User.COLUMN_EMAIL,
                        User.COLUMN_PASSWORD,
                        User.COLUMN_FULLNAME,
                        User.COLUMN_ADDRESS,
                        User.COLUMN_TIMESTAMP},
                User.COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null, null);

        boolean result = false;

        if (cursor != null){
            cursor.moveToFirst();
        }

        try{
            User data = new User();
            data.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));

            if(data.getPassword().equals(password)){
                result = true;
            } else {
                Toast.makeText(context, "password salah", Toast.LENGTH_LONG).show();
            }
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.e("ERROR", e.toString());
            Toast.makeText(context, "email dan password salah", Toast.LENGTH_LONG).show();
        }

        cursor.close();
        return result;
    }
}