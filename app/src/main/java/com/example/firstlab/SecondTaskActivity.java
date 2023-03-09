package com.example.firstlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firstlab.databinding.ActivityMainBinding;


public class SecondTaskActivity extends AppCompatActivity {

    static {
        System.loadLibrary("firstlab");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_task_activity);
        Button nextbtn = findViewById(R.id.nextButton);
        Button backbtn = findViewById(R.id.backButton);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FirstTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onButtonClick (View view)
    {
        Toast.makeText(SecondTaskActivity.this, "Hello", Toast.LENGTH_SHORT).show();
    }

}