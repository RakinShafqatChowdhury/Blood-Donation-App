package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {
    private Spinner bloodgrpSP;
    private String bloodgroup;
    private Button nearby;
    private TextView callBB,addBB,callBB1,addBB1;
    private TableLayout TL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        bloodgrpSP = findViewById(R.id.bloodgrpSP);
        callBB = findViewById(R.id.bbphnID);
        addBB = findViewById(R.id.bbaddID);
        callBB1 = findViewById(R.id.bbphnID1);
        addBB1 = findViewById(R.id.bbaddID1);
        nearby = findViewById(R.id.nearbyBtn);
        TL = findViewById(R.id.bloodbankID);
        String[] bloodgroups = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,bloodgroups);
        bloodgrpSP.setAdapter(adapter);
        TL.setVisibility(View.GONE);
        bloodgrpSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    bloodgroup = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TL.setVisibility(View.VISIBLE);
            }
        });

    }

    public void emergency_request(View view) {

        Toast.makeText(this, "We are working on this. Wait for the update.", Toast.LENGTH_SHORT).show();
    }

    public void bbphone(View view) {
        String phoneNum = callBB.getText().toString();String phoneNum1 = callBB1.getText().toString();
        Uri phoneUri = Uri.parse("tel:"+phoneNum);Uri phoneUri1 = Uri.parse("tel:"+phoneNum1);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL,phoneUri);Intent dialIntent1 = new Intent(Intent.ACTION_DIAL,phoneUri1);
        startActivity(dialIntent);startActivity(dialIntent1);
    }

    public void bbaddress(View view) {
        String address = addBB.getText().toString();String address1 = addBB1.getText().toString();
        Uri bdblUri = Uri.parse("geo:0,0?q="+address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, bdblUri);
        startActivity(mapIntent);
        Uri bdblUri1 = Uri.parse("geo:0,0?q="+address1);
        Intent mapIntent1 = new Intent(Intent.ACTION_VIEW, bdblUri1);
        startActivity(mapIntent1);

    }
}
