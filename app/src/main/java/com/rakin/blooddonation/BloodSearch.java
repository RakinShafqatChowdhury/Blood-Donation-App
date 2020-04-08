package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.List;

public class BloodSearch extends AppCompatActivity {
    int position = 0;
    private Spinner bloodSP;
    private EditText areaET;
    private Button call,sms;
    private String bloodgroup = "";
    private RecyclerView rcv;
    private donorRVadapter donorAdapter;
    private TextView total_donor,matchedFound;
    private String area = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_search);

        areaET = findViewById(R.id.areaET);
        bloodSP = findViewById(R.id.bloodSP);
        rcv = findViewById(R.id.donorRV);
        call = findViewById(R.id.call_row);
        sms = findViewById(R.id.sms_row);
        matchedFound = findViewById(R.id.matchedFoundTV);
        String[] blood_groups = getResources().getStringArray(R.array.blood_group);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,blood_groups);
        bloodSP.setAdapter(adapter);

        bloodSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){

                    bloodgroup = parent.getItemAtPosition(position).toString();

                }else{
                    bloodgroup="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(BloodSearch.this, "No bloodgroup selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void findBlood(View view) {
        String area = areaET.getText().toString();
        String bloodgrp = bloodgroup;

        if (bloodgrp.length() != 0 && area.length() != 0) {
            List<donorInfo> donor_BloodandAddress = DonorDatabase.getInstance(this)
                    .getDonorDao()
                    .matchedBloodandAddressList(bloodgrp, area);
            String matchedSize = Integer.toString(donor_BloodandAddress.size());
            donorAdapter = new donorRVadapter(this, donor_BloodandAddress);
            LinearLayoutManager LLM = new LinearLayoutManager(this);
            rcv.setLayoutManager(LLM);
            rcv.setAdapter(donorAdapter);
            matchedFound.setText("Matches Found:"+" "+matchedSize);


        } else if (bloodgrp.length() != 0 && area.length() == 0) {
            List<donorInfo> donor_Blood = DonorDatabase.getInstance(this)
                    .getDonorDao()
                    .matchedBloodList(bloodgrp);
            String matchedSize = Integer.toString(donor_Blood.size());
            donorAdapter = new donorRVadapter(this, donor_Blood);
            LinearLayoutManager LLM = new LinearLayoutManager(this);
            rcv.setLayoutManager(LLM);
            rcv.setAdapter(donorAdapter);
            matchedFound.setText("Matches Found:"+" "+matchedSize);

        } else if (bloodgrp.length() == 0 && area.length() != 0) {
            List<donorInfo> donor_Address = DonorDatabase.getInstance(this)
                    .getDonorDao()
                    .matchedAreaList(area);
            String matchedSize = Integer.toString(donor_Address.size());
            donorAdapter = new donorRVadapter(this, donor_Address);
            LinearLayoutManager LLM = new LinearLayoutManager(this);
            rcv.setLayoutManager(LLM);
            rcv.setAdapter(donorAdapter);
            matchedFound.setText("Matches Found:"+" "+matchedSize);

        } else {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show();
        }


    }



        /*if(area!=null && bloodgrp!=null){
            List<donorInfo> donor_BloodandAddress = DonorDatabase.getInstance(this)
                    .getDonorDao()
                    .matchedBloodandAddressList(bloodgrp,area);

            donorAdapter = new donorRVadapter(this,donor_BloodandAddress);
            LinearLayoutManager LLM = new LinearLayoutManager(this);
            rcv.setLayoutManager(LLM);
            rcv.setAdapter(donorAdapter);
        }*/



}
