package com.example.tridentapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    TextView name;
    SharedPreference sharedPreference;
    String Name;
    Timer timer;
    Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.ViewPager);
        name = view.findViewById(R.id.name);
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);

        sharedPreference = new SharedPreference(getActivity());
        Name = sharedPreference.getValue_string("name");

        name.setText("Welcome " + Name);

        MyAdapter myAdapter = new MyAdapter(imageList);
        viewPager.setAdapter(myAdapter);
        handler=new Handler();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int i=viewPager.getCurrentItem();
                    i++;
                    viewPager.setCurrentItem(i,true);
                }
            });
            }
        },2000,2000);

        return view;
    }
}