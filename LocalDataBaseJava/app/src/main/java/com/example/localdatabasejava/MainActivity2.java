package com.example.localdatabasejava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabasejava.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DbHelper(getApplicationContext());



        binding.btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = binding.edtname.getText().toString();
                String email = binding.edtemail.getText().toString();
                String pass = binding.edtpass.getText().toString();

                if (name.length() == 0 && email.length() == 0 && pass.length() == 0) {
                    binding.edtname.setError("Please enter name");
                    binding.edtemail.setError("Please enter email");
                    binding.edtpass.setError("Please enter password");
                }
                else if (name.length()==0)
                {
                    binding.edtname.setError("Please enter name");
                }
                else if (email.length()==0)
                {
                    binding.edtemail.setError("Please enter email");
                }
                else if (pass.length()==0)
                {
                    binding.edtpass.setError("Please enter password");
                }
                else
                {

                    Model m = new Model();
                    m.name = name;
                    m.email = email;
                    m.pass = pass;

                    dbHelper.insertdata(m);

                    Toast.makeText(MainActivity2.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        binding.txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}