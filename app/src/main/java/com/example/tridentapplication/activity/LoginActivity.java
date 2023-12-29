package com.example.tridentapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tridentapplication.R;
import com.example.tridentapplication.util.SharedPreference;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {
    CircleImageView imageView;
    TextView welcomeTrident, Email, password, textRemember, textSignup;
    EditText Username, Password;
    Button button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreference sharedPreference;
    String email = "", confirmpassword = "", namee, regno, mob_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView = findViewById(R.id.selectImage);
        welcomeTrident = findViewById(R.id.welcometoTextView);
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        textRemember = findViewById(R.id.Remember);
        textSignup = findViewById(R.id.Signup);
        Username = findViewById(R.id.edEmail_ID);
        Password = findViewById(R.id.editpassword);
        button = findViewById(R.id.Login);

        sharedPreference = new SharedPreference(LoginActivity.this);

        email = sharedPreference.getValue_string("email");
        confirmpassword = sharedPreference.getValue_string("confirmpassword");
        namee = sharedPreference.getValue_string("name");
        regno = sharedPreference.getValue_string("regno");
        mob_no = sharedPreference.getValue_string("mob_no");
        button.setOnClickListener(view -> {
            String username = Username.getText().toString();
            String password = Password.getText().toString();

            if (username.equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
            } else if (!username.matches(emailPattern)) {
                Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            } else if (!username.equals(email)) {
                Toast.makeText(LoginActivity.this, "username is not registered", Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(LoginActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmpassword)) {
                Toast.makeText(LoginActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

        });
        textSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

}