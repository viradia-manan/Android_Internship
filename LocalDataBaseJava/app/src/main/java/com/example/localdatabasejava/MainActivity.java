package com.example.localdatabasejava;

import static com.example.localdatabasejava.DbHelper.id;
import static com.example.localdatabasejava.DbHelper.name;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabasejava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DbHelper DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DBHelper = new DbHelper(getApplicationContext());

        SharedPreferences sh = getSharedPreferences("session", MODE_PRIVATE);

        if (sh.getBoolean("session", false) && !sh.getString("email", "").isEmpty()) {
            Intent i = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(i);
        }

        binding.txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = binding.edtemail.getText().toString();
                String pass = binding.edtpass.getText().toString();

                if (email.length() == 0 && pass.length() == 0)
                {
                    binding.edtemail.setError("Please enter email");
                    binding.edtpass.setError("Please enter password");
                }
                else if (email.length() == 0)
                {
                    binding.edtemail.setError("Please enter email");
                }
                else if (pass.length() == 0)
                {
                    binding.edtpass.setError("Please enter password");
                }
                else
                {

                    SQLiteDatabase db = DBHelper.getReadableDatabase();

                    String[] projection = {DBHelper.id, DBHelper.name};
                    String selection = DBHelper.email + "=? AND " + DBHelper.pass + "=?";
                    String[] selectionArgs = {email, pass};

                    Cursor cursor;

                    cursor = db.query(DBHelper.tablename, projection, selection, selectionArgs, null, null, null);

                    if (cursor.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                        //userdata
                        @SuppressLint("Range") int userId = (int) cursor.getLong(cursor.getColumnIndex(DBHelper.id));
                        @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex(DBHelper.name));

                        //sharedpreferences
                        SharedPreferences sh = getSharedPreferences("session", Context.MODE_PRIVATE);


                        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor sf = sh.edit();
                        sf.putBoolean("session", true);
                        sf.putInt("id", userId);
                        sf.putString("name", userName);
                        sf.putString("email", email);
                        sf.apply();
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }

                    cursor.close();
                    db.close();
                }
            }
        });
    }
}
