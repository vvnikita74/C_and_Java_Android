package com.example.firstlab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    ActivityResultLauncher activityResultLauncher;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_task_activity);
        Button nextBtn = findViewById(R.id.nextButton);
        Button backBtn = findViewById(R.id.backButton);
        TextView text = findViewById(R.id.PinCode);

        activityResultLauncher  = registerForActivityResult(
                                    new ActivityResultContracts.StartActivityForResult(),
                                    new ActivityResultCallback<ActivityResult>() {
                                        @Override
                                        public void onActivityResult(ActivityResult result) {
                                            if (result.getResultCode() == Activity.RESULT_OK) {
                                                Intent data = result.getData();
                                                // обработка результата
                                                text.setText("Вы ввели: " + data.getStringExtra("pin"));
                                                // Toast.makeText(SecondTaskActivity.this, pin, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
        );

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
        activityResultLauncher.launch(intent);
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