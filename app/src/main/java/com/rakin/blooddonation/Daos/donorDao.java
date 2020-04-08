package com.rakin.blooddonation.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rakin.blooddonation.Entities.donorInfo;
import com.rakin.blooddonation.pojos.RegUserCheck;
import com.rakin.blooddonation.pojos.UserEmailPassword;

import java.util.List;

@Dao
public interface donorDao {
    @Insert
    long insertDonor(donorInfo donorinfo);

    @Query("select * from donor_list where d_email like :email")
    donorInfo getDonorInfo(String email);


    @Query("SELECT COUNT(d_email) FROM donor_list WHERE d_email = d_email")
    String getNumberOfRows();

  /*  @Query("select donor_id from donor_list where d_email like :email")
    int getUserId(String email);*/

    @Query("select d_name from donor_list where d_email like :email")
    String getUsername(String email);
    @Query("select d_bloodgrp from donor_list where d_email like :email")
    String getUserBloodGroup(String email);

    /*@Query("select d_age from donor_list where d_email like :email")
    String getUserage(String email);
    @Query("select d_phone from donor_list where d_email like :email")
    String getUserphone(String email);
    @Query("select d_area from donor_list where d_email like :email")
    String getUserarea(String email);
    @Query("select d_city from donor_list where d_email like :email")
    String getUsercity(String email);
    @Query("select d_email from donor_list where d_email like :email")
    String getUsermail(String email);
    @Query("select d_pass from donor_list where d_email like :email")
    String getUserpass(String email);
    @Query("select d_bloodgrp from donor_list where d_email like :email")
    String getUserbg(String email);*/


    @Query("select d_email from donor_list where d_email like :email")
    RegUserCheck getEmail(String email);

    @Query("select d_phone from donor_list where d_phone like :phone")
    RegUserCheck getPhone(String phone);

    @Query("select d_email, d_pass from donor_list where d_email like :email and d_pass like :password")
    UserEmailPassword getEmailAndPassword(String email, String password);

    @Query("select * from donor_list where d_bloodgrp like :bloodgrp")
    List<donorInfo> matchedBloodList(String bloodgrp);

    @Query("select * from donor_list where d_area like :area")
    List<donorInfo> matchedAreaList(String area);

    @Query("select * from donor_list where d_bloodgrp like :bloodgrp and d_area like :area")
    List<donorInfo> matchedBloodandAddressList(String bloodgrp,String area);


    @Update
    int updateDonorList(donorInfo donorInfo);

    @Delete
    int deleteDonorList(donorInfo donorInfo);

}
