package com.example.tridentapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tridentapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FlashActivity extends AppCompatActivity {
 private CircleImageView image;
 private TextView tridentCom;
 private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        image =findViewById(R.id.image);
        tridentCom =findViewById(R.id.textView);
        start =findViewById(R.id.Start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(FlashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}