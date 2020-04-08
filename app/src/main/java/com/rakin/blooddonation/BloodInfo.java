package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BloodInfo extends AppCompatActivity {
    private Spinner spinner;
    private String bloodMatch="A+";
    private TextView matchTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);
        spinner = findViewById(R.id.spinnerCheck);
        matchTV = findViewById(R.id.matchTV);
        final String[] check_blood = getResources().getStringArray(R.array.bloodmatch_group);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,check_blood);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    bloodMatch = parent.getItemAtPosition(position).toString();
                    Toast.makeText(BloodInfo.this, "Selected: "+bloodMatch, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void check_match(View view) {
        if(bloodMatch.equals("A+")){
            matchTV.setText("You can receive blood from A(+ve), A(-ve), O(+ve) and O(-ve).\nYou can give blood to A(+ve) and AB(+ve)");
        }
        if(bloodMatch.equals("A-")){
            matchTV.setText("You can receive A(-ve) and O(-ve) blood.\nYou can give blood to A(+ve), A(-ve), AB(+ve) and AB(-ve)");
        }
        if(bloodMatch.equals("B+")){
            matchTV.setText("You can receive blood from B(+ve), B(-ve), O(+ve) and O(-ve).\nYou can give blood to B(+ve) and AB(+ve)");
        }
        if(bloodMatch.equals("B-")){
            matchTV.setText("You can receive blood from B(-ve) and O(-ve).\nYou can give blood to B(+ve), B(-ve), AB(+ve) and AB(-ve)");
        }
        if(bloodMatch.equals("AB+")){
            matchTV.setText("You can receive blood from Everyone.\nYou can give blood to only AB(+ve)");
        }
        if(bloodMatch.equals("AB-")){
            matchTV.setText("You can receive blood from AB(-ve), A(-ve), B(+ve) and O(-ve).\nYou can give blood to AB(+ve) and AB(-ve)");
        }
        if(bloodMatch.equals("O+")){
            matchTV.setText("You can receive blood from O(+ve) and O(-ve).\nYou can give blood to A(+ve), B(+ve), AB(+ve) and O(+ve)");
        }
        if(bloodMatch.equals("O-")){
            matchTV.setText("You can receive blood from only O(-ve).\nYou can give blood to Everyone");
        }
    }
}
