package com.example.tridentapplication.model;

import android.graphics.Bitmap;

public class DataModel {
    public static String name = "";
    public static String email = "";
    public static String password = "";
    public static String regno = "";
    public static String mob_no = "";
    public static Bitmap image;

    public static Bitmap getImage() {
        return image;
    }


    public void setImage(Bitmap image) {
        this.image = image;
    }



    public DataModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

}
