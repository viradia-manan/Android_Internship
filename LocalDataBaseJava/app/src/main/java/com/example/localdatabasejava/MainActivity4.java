package com.example.localdatabasejava;

import static com.example.localdatabasejava.DbHelper.id;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabasejava.databinding.ActivityMain3Binding;
import com.example.localdatabasejava.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DbHelper(getApplicationContext());

        SharedPreferences sh = getSharedPreferences("session", MODE_PRIVATE);
        int id = sh.getInt("id",101);

        Intent i = getIntent();
        binding.name.setText(i.getStringExtra("name"));
        binding.email.setText(i.getStringExtra("email"));

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();

                Model m = new Model();
                m.id = id;
                m.name=name;
                m.email=email;

                dbHelper.updatedata(m);

                Toast.makeText(MainActivity4.this, "Update Profile Successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(i);

            }
        });

    }
}