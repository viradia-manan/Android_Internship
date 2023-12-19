package com.example.localdatabasejava;

import static com.example.localdatabasejava.DbHelper.id;
import static com.example.localdatabasejava.DbHelper.name;
import static com.example.localdatabasejava.DbHelper.tablename;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabasejava.databinding.ActivityMain2Binding;
import com.example.localdatabasejava.databinding.ActivityMain3Binding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    DbHelper DBHelper;
    String username;
    String useremail;
    int userid;



    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        SharedPreferences sh = getSharedPreferences("session", MODE_PRIVATE);
        String id = String.valueOf(sh.getInt("id",101));

        DbHelper DbHelper = new DbHelper(getApplicationContext());

        SQLiteDatabase db = DbHelper.getReadableDatabase();
        String[] projection = {DBHelper.id, DBHelper.name,DBHelper.email};
        String selection = DBHelper.id + "=" + id ;
        Cursor cursor;

        cursor = db.query(DBHelper.tablename, projection, selection, null, null, null, null);

        if (cursor.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

            //userdata
            @SuppressLint("Range") int userId = (int) cursor.getLong(cursor.getColumnIndex(DBHelper.id));
            @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex(DBHelper.name));
            String userEmail = cursor.getString(cursor.getColumnIndex(DBHelper.email));

            binding.name.setText("Welcome " + userName);

            userid = userId;
            username = userName;
            useremail = userEmail;


        }


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor sf = sh.edit();
                sf.clear();
                sf.commit();

                Intent i = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(i);
            }
        });


        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this,MainActivity4.class);
                i.putExtra("id",userid);
                i.putExtra("email",useremail);
                i.putExtra("name",username);
                startActivity(i);
            }
        });
    }
}