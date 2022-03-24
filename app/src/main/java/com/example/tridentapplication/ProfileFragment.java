package com.example.tridentapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    SharedPreference sharedPreference;
    String name,email,confirmpassword,regno,mob_no;
    TextView Name,Email,Password,Regno,Mob_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_profile, container, false);
        Name = view.findViewById(R.id.name);
        Email =view.findViewById(R.id.email);
        Password =view.findViewById(R.id.password);
        Regno =view.findViewById(R.id.regn);
        Mob_no =view.findViewById(R.id.mob_no);
        sharedPreference = new SharedPreference(getActivity());

        name = sharedPreference.getValue_string("name");
        email = sharedPreference.getValue_string("email");
        confirmpassword = sharedPreference.getValue_string("confirmpassword");
        regno = sharedPreference.getValue_string("regno");
        mob_no =sharedPreference.getValue_string("mob_no");
        Name.setText(name);
        Email.setText(email);
        Password.setText(confirmpassword);
        Regno.setText(regno);
        Mob_no.setText(mob_no);

        return view;
    }

}