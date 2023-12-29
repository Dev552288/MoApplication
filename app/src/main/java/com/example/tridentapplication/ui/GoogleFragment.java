package com.example.tridentapplication.ui;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tridentapplication.R;

import java.util.Calendar;


public class GoogleFragment extends Fragment {
    CardView assigementView, eventView;
    ImageView assigementImage, eventImage;
    SearchView googleSearchView;
    TextView textView;
    TextView Date;
    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog datePicker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);


        openGoogleBrowser();
        return view;
    }

    private void openGoogleBrowser(){
        Uri uri = Uri.parse("http://google.com");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.google.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://google.com")));
        }
    }

}