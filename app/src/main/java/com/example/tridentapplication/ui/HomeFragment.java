package com.example.tridentapplication.ui;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tridentapplication.model.DataModel;
import com.example.tridentapplication.model.adapter.MyAdapter;
import com.example.tridentapplication.R;
import com.example.tridentapplication.util.SharedPreference;
import com.example.tridentapplication.activity.HomeActivity;
import com.google.android.material.navigation.NavigationView;

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
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    HomeActivity activity;
    DataModel dataModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        navigationView = view.findViewById(R.id.navigationbar);
        drawerLayout = view.findViewById(R.id.drawer);

        toolbar = view.findViewById(R.id.toolbar);

        sharedPreference = new SharedPreference(requireContext());

        viewPager = view.findViewById(R.id.ViewPager);
        name = view.findViewById(R.id.name);
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        String image = sharedPreference.getValue_string("image");
        if (image.equalsIgnoreCase("")){
            setHeaderImage();
        }else {
            byte [] b = Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
            View headerView = navigationView.getHeaderView(0);
            ImageView drawerImage = (ImageView) headerView.findViewById(R.id.hederImage);
            drawerImage.setImageBitmap(bitmap);

        }
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Toast.makeText(getActivity(), "Home panel is open", Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.setting:
                    Toast.makeText(getActivity(), "Setting panel is open", Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    InstagramFragment NAME = new InstagramFragment();
                    fragmentTransaction.replace(R.id.fragment_container, NAME);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.attendance:
                    Toast.makeText(getActivity(), "Attendance panel is open", Toast.LENGTH_LONG).show();
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    GoogleFragment attendanceFragment = new GoogleFragment();
                    fragmentTransaction.replace(R.id.fragment_container, attendanceFragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.profile:
                    Toast.makeText(getActivity(), "Profile panel is open", Toast.LENGTH_LONG).show();
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    FacebookFragment resultFragment = new FacebookFragment();
                    fragmentTransaction.replace(R.id.fragment_container, resultFragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.logout:
                    Toast.makeText(getActivity(),"Logout Successful",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    break;
            }
            return true;
        });


        sharedPreference = new SharedPreference(getActivity());
        Name = sharedPreference.getValue_string("name");

        name.setText("Welcome " + Name);

        MyAdapter myAdapter = new MyAdapter(imageList);
        viewPager.setAdapter(myAdapter);
        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    int i = viewPager.getCurrentItem();
                    i++;
                    viewPager.setCurrentItem(i, true);
                });
            }
        }, 2000, 2000);
        return view;
    }



    private void setHeaderImage() {
        View headerView = navigationView.getHeaderView(0);
        ImageView drawerImage = (ImageView) headerView.findViewById(R.id.hederImage);
        dataModel = new DataModel();
        Bitmap image = dataModel.getImage();
        Log.e(TAG, "HeaderImage: "+image);
        drawerImage.setImageBitmap(image);
    }

}