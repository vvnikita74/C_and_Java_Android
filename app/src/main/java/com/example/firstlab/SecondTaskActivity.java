package com.example.firstlab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstlab.databinding.ActivityMainBinding;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


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
        Button nextBtn = findViewById(R.id.nextButton);
        Button backBtn = findViewById(R.id.backButton);

        nextBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        });

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FirstTaskActivity.class);
            startActivity(intent);
        });
    }

    public void onButtonClick (View view) {
        Intent intent = new Intent(getApplicationContext(), PinpadActivity.class);
        startActivity(intent);
    }

    public static byte[] stringToHex(String s) {
        byte[] hex;
        try
        {
            hex = Hex.decodeHex(s.toCharArray());
        }
        catch (DecoderException ex)
        {
            hex = null;
        }
        return hex;
    }
}