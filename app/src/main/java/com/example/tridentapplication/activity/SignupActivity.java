package com.example.tridentapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tridentapplication.ui.InstagramFragment;
import com.example.tridentapplication.R;
import com.example.tridentapplication.util.SharedPreference;
import com.example.tridentapplication.model.DataModel;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {
    CircleImageView signImage;
    EditText signEmail, createPassword, confirmPassword, signName, signMobileno;
    TextView signtextView, alredyRegister;
    Button signupButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreference sharedPreference;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_REQUEST = 2;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signImage = findViewById(R.id.selectImage);
        signtextView = findViewById(R.id.sign_TextView);
        alredyRegister = findViewById(R.id.Already_Register);
        signEmail = findViewById(R.id.Sign_Email);
        createPassword = findViewById(R.id.Create_Password);
        confirmPassword = findViewById(R.id.Confirm_Password);
        signName = findViewById(R.id.sign_Name);
        signMobileno = findViewById(R.id.Sign_Mobileno);
        signupButton = findViewById(R.id.Signup);
        sharedPreference = new SharedPreference(SignupActivity.this);
        signImage.setOnClickListener(view -> {
            final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
            builder.setTitle("Update Photo!");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, CAMERA_REQUEST);
                            }
                        }
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_REQUEST);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });

        signupButton.setOnClickListener(view -> {
            String email = signEmail.getText().toString();
            String password = createPassword.getText().toString();
            String confirmpassword = confirmPassword.getText().toString();
            String name = signName.getText().toString();
            String mobileno = signMobileno.getText().toString();
            sharedPreference.setValue_string("email", email);
            sharedPreference.setValue_string("confirmpassword", confirmpassword);
            sharedPreference.setValue_string("name", name);
            sharedPreference.setValue_string("mob_no", mobileno);

            DataModel dataModel = new DataModel();
            dataModel.setName(name);
            dataModel.setEmail(email);
            dataModel.setPassword(password);
            dataModel.setMob_no(mobileno);

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
            }else if (mobileno.equals("")) {
                Toast.makeText(SignupActivity.this, "Enter your Mobile no", Toast.LENGTH_SHORT).show();
            } else if (mobileno.length() < 10) {
                Toast.makeText(SignupActivity.this, "mobile no should be at least 10 digit", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SignupActivity.this, "Signup is successful", Toast.LENGTH_SHORT).show();
            }
        });

        alredyRegister.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = new Bundle();
            InstagramFragment profileFragment = new InstagramFragment();
            DataModel dataModel = new DataModel();
            if (requestCode == CAMERA_REQUEST) {
                photo = (Bitmap) data.getExtras().get("data");
                Log.d("Image ","Image"+photo);
                signImage.setImageBitmap(photo);
                bundle.putString("image", String.valueOf(photo));
                profileFragment.setArguments(bundle);
                dataModel.setImage(photo);
            } else if (requestCode == GALLERY_REQUEST && data.getData() != null) {
                Uri fileuri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = this.getContentResolver().openInputStream(fileuri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                photo = BitmapFactory.decodeStream(imageStream);
                signImage.setImageBitmap(photo);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte [] bytes = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.encodeToString(bytes,Base64.DEFAULT);
                sharedPreference.setValue_string("image", encodedImage);
                Toast.makeText(this, "Image saved in sharedPreferences", Toast.LENGTH_SHORT).show();

                dataModel.setImage(photo);
                Log.e("image", "Image: " + photo);

                bundle.putString("image", String.valueOf(photo));
                profileFragment.setArguments(bundle);
                profileFragment.setArguments(bundle);

            }
        }
    }
}