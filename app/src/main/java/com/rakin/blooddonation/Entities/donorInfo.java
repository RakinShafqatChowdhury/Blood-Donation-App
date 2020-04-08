package com.rakin.blooddonation.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "donor_list")
public class donorInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int donor_id;
    @ColumnInfo(name = "d_name")
    private String donor_name;
    @ColumnInfo(name = "d_age")
    private String age;
    @ColumnInfo(name = "d_phone")
    private String phone;
    @ColumnInfo(name = "d_area")
    private String area;
    @ColumnInfo(name = "d_city")
    private String city;
    @ColumnInfo(name = "d_email")
    private String email;
    @ColumnInfo(name = "d_pass")
    private String password;
    @ColumnInfo(name = "d_bloodgrp")
    private String blood_grp;
    @Ignore
    private int count = 0;

    public donorInfo(String donor_name, String age, String phone, String area, String city, String email, String password, String blood_grp) {
        this.donor_name = donor_name;
        this.age = age;
        this.phone = phone;
        this.area = area;
        this.city = city;
        this.email = email;
        this.password = password;
        this.blood_grp = blood_grp;
    }
    @Ignore
    public donorInfo(int donor_id, String donor_name, String age, String phone, String area, String city, String email, String password, String blood_grp) {
        this.donor_id = donor_id;
        this.donor_name = donor_name;
        this.age = age;
        this.phone = phone;
        this.area = area;
        this.city = city;
        this.email = email;
        this.password = password;
        this.blood_grp = blood_grp;
    }



    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlood_grp() {
        return blood_grp;
    }

    public void setBlood_grp(String blood_grp) {
        this.blood_grp = blood_grp;
    }
}
