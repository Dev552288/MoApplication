package com.example.tridentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {
    CircleImageView signImage;
    EditText signEmail, createPassword, confirmPassword, signName, regNo, signMobileno;
    TextView signtextView, alredyRegister;
    Button Sign;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signImage = findViewById(R.id.circleImageView);
        signtextView = findViewById(R.id.sign_TextView);
        alredyRegister = findViewById(R.id.Already_Register);
        signEmail = findViewById(R.id.Sign_Email);
        createPassword = findViewById(R.id.Create_Password);
        confirmPassword = findViewById(R.id.Confirm_Password);
        signName = findViewById(R.id.Sign_Name);
        regNo = findViewById(R.id.Regn);
        signMobileno = findViewById(R.id.Sign_Mobileno);
        Sign = findViewById(R.id.Signup);

        sharedPreference = new SharedPreference(SignupActivity.this);

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signEmail.getText().toString();
                String password = createPassword.getText().toString();
                String confirmpassword = confirmPassword.getText().toString();
                String name = signName.getText().toString();
                String regno = regNo.getText().toString();
                String mobileno = signMobileno.getText().toString();



                if (email.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(SignupActivity.this, "Create your password", Toast.LENGTH_SHORT).show();
                } else if (confirmpassword.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                } else if (!confirmpassword.matches(password)) {
                    Toast.makeText(SignupActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                } else if (name.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                } else if (regno.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter your registration no", Toast.LENGTH_SHORT).show();
                } else if (mobileno.equals("")) {
                    Toast.makeText(SignupActivity.this, "Enter your Mobile no", Toast.LENGTH_SHORT).show();
                }else if (mobileno.length()<10){
                    Toast.makeText(SignupActivity.this, "mobile no should be atleat 10 digit", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Signup is successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);

                    sharedPreference.setValue_string("email",email);
                    sharedPreference.setValue_string("confirmpassword",confirmpassword);
                    sharedPreference.setValue_string("name",name);
                    sharedPreference.setValue_string("regno",regno);
                    sharedPreference.setValue_string("mob_no",mobileno);
                    DataModel dataModel = new DataModel();
                    dataModel.setName(name);
                    dataModel.setEmail(email);
                    dataModel.setPassword(password);
                    dataModel.setRegno(regno);
                    dataModel.setMob_no(mobileno);
                    startActivity(intent);
                }
            }
        });

        alredyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}