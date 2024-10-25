package com.example.tridentapplication.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tridentapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FlashActivity extends AppCompatActivity {
 private TextView tridentCom;
 private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tridentCom =findViewById(R.id.textView);
        start = findViewById(R.id.Start);
        new Handler(Looper.myLooper()).postDelayed(() -> {
            Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}