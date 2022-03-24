package com.example.tridentapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment {
    TextView Name,Email,Password,Regno,Mob_no;
    String name,email,password,regno,mobno;
    DataModel dataModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_result, container, false);
        Name =view.findViewById(R.id.name);
        Email =view.findViewById(R.id.email);
        Password =view.findViewById(R.id.password);
        Regno =view.findViewById(R.id.regno);
        Mob_no =view.findViewById(R.id.mob_no);
        
       //datamodel class object
        DataModel dataModel= new DataModel();
        String name=dataModel.getName();
        Name.setText(name);
        String email=dataModel.getEmail();
        Email.setText(email);
        String password=dataModel.getPassword();
        Password.setText(password);
        String regno=dataModel.getRegno();
        Regno.setText(regno);
        String mobno=dataModel.getMob_no();
        Mob_no.setText(mobno);
        return view;
    }
}