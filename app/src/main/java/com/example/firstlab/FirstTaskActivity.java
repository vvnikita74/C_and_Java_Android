package com.example.firstlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstlab.databinding.ActivityMainBinding;

import java.nio.charset.StandardCharsets;

public class FirstTaskActivity extends AppCompatActivity {

    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static native byte[] encrypt(byte[] key, byte[] array);
    public static native byte[] decrypt(byte[] key, byte[] array);

    static {
        System.loadLibrary("firstlab");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_task_activity);

        TextView textfun1 = findViewById(R.id.sample_text);
        TextView textfun2 = findViewById(R.id.sample_text2);
        TextView textfun3 = findViewById(R.id.sample_text3);
        TextView textfun4 = findViewById(R.id.sample_text4);
        TextView textfun5 = findViewById(R.id.sample_text5);

        Button nextbtn = (Button) findViewById(R.id.NextButton);
        Button backbtn = (Button) findViewById(R.id.BackButton);

        // Testing C++ functions

        // Testing initRng - must be 0 (success initialization)
        int res = initRng();
        textfun1.setText("initRng: " + Integer.toString(res));

        // Testing randomBytes - random bytes output
        byte [] rnd_bytes = randomBytes(16);
        String rnd_str = new String(rnd_bytes, StandardCharsets.UTF_16);
        textfun2.setText("randomBytes: " + rnd_str);

        // Testing stringFromJNI - print a predefined string
        textfun3.setText("stringFromJNI: " + stringFromJNI());

        // --Testing encryption functions--

        // Encrypt
        String str = "Sample Text";
        byte [] byteArr = str.getBytes(StandardCharsets.UTF_16);
        byte [] es = encrypt(rnd_bytes, byteArr);
        String es_str = new String(es, StandardCharsets.UTF_16);
        textfun4.setText("ecnrypt function: " + es_str);

        // Decrypt
        byte [] ds = decrypt(rnd_bytes, es);
        String ds_str = new String(ds, StandardCharsets.UTF_16);
        textfun5.setText("decrypt function: " + ds_str);

        //-- --

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FirstTaskActivity.class);
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}