package com.example.localdatabasejava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String dbname = "user";
    public static final String tablename = "info";
    public static final String id = "id";

    public static  final String name = "name";
    public static final String email = "email";
    public static final String pass = "pass";
    public static final int dbversion = 9;

    public DbHelper(Context context) {
        super(context, dbname, null, dbversion);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + tablename + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, pass TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String upquery = "DROP TABLE IF EXISTS " + tablename;
        db.execSQL(upquery);
        onCreate(db);

    }

    public boolean insertdata(Model m) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "INSERT INTO " + tablename + " (name, email, pass) VALUES ('" + m.name + "', '" + m.email + "', '" + m.pass + "')";
        db.execSQL(insert);
        return true;
    }


    public boolean updatedata(Model m) {
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE " + tablename + " SET name = '" + m.name + "', email = '" + m.email + "' WHERE id = " + m.id;
        db.execSQL(update);
        return true;
    }

    /*public ArrayList viewdata(Model m)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tablename + " WHERE id = " + m.id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(String.valueOf(0)));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(String.valueOf(1)));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(String.valueOf(2)));
                @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex(String.valueOf(3)));
                Model model = new Model();
                m.id = id;
                m.name = name;
                m.email = email;
                m.pass = pass;

                ArrayList<Model> list = new ArrayList<Model>();
                list.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }*/

}
