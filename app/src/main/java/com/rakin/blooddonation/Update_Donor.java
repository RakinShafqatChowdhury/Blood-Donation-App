package com.rakin.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rakin.blooddonation.DB.DonorDatabase;
import com.rakin.blooddonation.Entities.donorInfo;
import com.rakin.blooddonation.pojos.RegUserCheck;

import java.util.List;

public class Update_Donor extends AppCompatActivity {
    private EditText nameET,ageET,phoneET,areaET,cityET,emailET,passET;
    private Spinner bloodgrpSP;
    private String bloodgroup;
    private Button yes,no;
    private int userId;
    private String name;

    private donorInfo donorInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__donor);
        Bundle bundle = getIntent().getExtras();
        String message1 = bundle.getString("message1");
        Toast.makeText(this, message1, Toast.LENGTH_SHORT).show();

        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        phoneET = findViewById(R.id.phoneET);
        areaET = findViewById(R.id.areaET);
        cityET = findViewById(R.id.cityET);
        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passwordLogin);
        bloodgrpSP = findViewById(R.id.bloodgrpSP);


        donorInfo = (com.rakin.blooddonation.Entities.donorInfo) getIntent().getSerializableExtra("donorInfo");
        nameET.setText(donorInfo.getDonor_name());
        ageET.setText(donorInfo.getAge());
        phoneET.setText(donorInfo.getPhone());
        areaET.setText(donorInfo.getArea());
        cityET.setText(donorInfo.getCity());
        emailET.setText(donorInfo.getEmail());
        passET.setText(donorInfo.getPassword());
        emailET.setEnabled(false);

       //bloodgroup = DonorDatabase.getInstance(this).getDonorDao().getUserbg(message1);
       /* userId = DonorDatabase.getInstance(this).getDonorDao().getUserId(message1);
        nameET.setText(DonorDatabase.getInstance(this).getDonorDao().getUsername(message1));
        ageET.setText(DonorDatabase.getInstance(this).getDonorDao().getUserage(message1));
        phoneET.setText(DonorDatabase.getInstance(this).getDonorDao().getUserphone(message1));
        areaET.setText(DonorDatabase.getInstance(this).getDonorDao().getUserarea(message1));
        cityET.setText(DonorDatabase.getInstance(this).getDonorDao().getUsercity(message1));
        emailET.setText(DonorDatabase.getInstance(this).getDonorDao().getUsermail(message1));
        passET.setText(DonorDatabase.getInstance(this).getDonorDao().getUserpass(message1));*/

        String[] bloodgroups = getResources().getStringArray(R.array.blood_group);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,bloodgroups);
        bloodgrpSP.setAdapter(adapter);

        bloodgrpSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    bloodgroup = parent.getItemAtPosition(position).toString();

                }else{

                    int spinnerPosition = adapter.getPosition(donorInfo.getBlood_grp());
                    bloodgroup=parent.getItemAtPosition(spinnerPosition).toString();
                    bloodgrpSP.setSelection(spinnerPosition);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Update_Donor.this, "No bloodgroup selected", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateDonor(View view) {

        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        String phone = phoneET.getText().toString();
        String area = areaET.getText().toString();
        String city = cityET.getText().toString();
        String email = emailET.getText().toString();
        String password = passET.getText().toString();
        String blood_group = bloodgroup;
        if(checkEmptyFields()){
            if(email!=null){

//                if(blood_group.length()==0){
//                    Toast.makeText(this, "Choose your bloodgroup", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                donorInfo.setDonor_name(name);
                donorInfo.setAge(age);
                donorInfo.setPhone(phone);
                donorInfo.setArea(area);
                donorInfo.setCity(city);
                donorInfo.setEmail(email);
                donorInfo.setPassword(password);
                donorInfo.setBlood_grp(blood_group);

                //donorInfo donor_info = new donorInfo(userId,name,age,phone,area,city,email,password,blood_group);
                int updatedRow = DonorDatabase.getInstance(this).getDonorDao().updateDonorList(donorInfo);

                if (updatedRow > 0){
                    Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,donorActivity.class);
                    i.putExtra("email",email);
                    i.putExtra("bloodgrp",bloodgroup);
                    startActivity(i);

                }else{
                    Toast.makeText(this, "failed to update", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }

    public void deleteDonor(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.donor_delete,null);
        yes = v.findViewById(R.id.yesBtn);
        no = v.findViewById(R.id.noBtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_Donor();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    private void delete_Donor() {
        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        String phone = phoneET.getText().toString();
        String area = areaET.getText().toString();
        String city = cityET.getText().toString();
        String email = emailET.getText().toString();
        String password = passET.getText().toString();
        String blood_group = bloodgroup;

        donorInfo.setDonor_name(name);
        donorInfo.setAge(age);
        donorInfo.setPhone(phone);
        donorInfo.setArea(area);
        donorInfo.setCity(city);
        donorInfo.setEmail(email);
        donorInfo.setPassword(password);
        donorInfo.setBlood_grp(blood_group);

        //donorInfo donor_info = new donorInfo(userId,name,age,phone,area,city,email,password,blood_group);
        int updatedRow = DonorDatabase.getInstance(this).getDonorDao().deleteDonorList(donorInfo);

        if (updatedRow > 0){
            Toast.makeText(this, "Deleted profile", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("mail", email);i.putExtra("bg", bloodgroup);
            startActivity(i);

        }else{
            Toast.makeText(this, "failed to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkEmptyFields(){
        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        String phone = phoneET.getText().toString();
        String area = areaET.getText().toString();
        String city = cityET.getText().toString();
        String email = emailET.getText().toString();
        String password = passET.getText().toString();


        if(name.isEmpty()){
            nameET.setError("Please Enter your name");
            return false;
        }
        if(age.isEmpty()){
            ageET.setError("Please Enter your age");
            return false;
        }
        if(phone.isEmpty()){
            phoneET.setError("Please Enter your phone number");
            return false;
        }
        if(area.isEmpty()){
            areaET.setError("Please Enter your area");
            return false;
        }
        if(city.isEmpty()){
            cityET.setError("Please Enter your city");
            return false;
        }
        if(email.isEmpty()){
            emailET.setError("Please Enter valid email address");
            return false;
        }
        if(password.isEmpty()){
            passET.setError("Please Enter password");
            return false;
        }

        return true;
    }
}
