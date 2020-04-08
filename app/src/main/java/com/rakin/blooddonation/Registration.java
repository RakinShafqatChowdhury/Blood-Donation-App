package com.rakin.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rakin.blooddonation.DB.DonorDatabase;
import com.rakin.blooddonation.Entities.donorInfo;
import com.rakin.blooddonation.pojos.RegUserCheck;

public class Registration extends AppCompatActivity {
    private EditText nameET,ageET,phoneET,areaET,cityET,emailET,passET,bloodgrpET;
    private Spinner bloodgrpSP;
    private int count=0;
    private String bloodgroup = "";
    private Button okay;
    public int total_donor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        phoneET = findViewById(R.id.phoneET);
        areaET = findViewById(R.id.areaET);
        cityET = findViewById(R.id.cityET);
        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passwordLogin);
        bloodgrpSP = findViewById(R.id.bloodgrpSP);

        String[] bloodgroups = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,bloodgroups);
        bloodgrpSP.setAdapter(adapter);

        bloodgrpSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    bloodgroup = parent.getItemAtPosition(position).toString();
                    Toast.makeText(Registration.this, "selected"+bloodgroup, Toast.LENGTH_SHORT).show();
                }else{
                    bloodgroup="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Registration.this, "no bloodgroup selected", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void registration(View view) {
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

                if(blood_group.length()==0){
                    Toast.makeText(this, "Choose your bloodgroup", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(phone!=null){
                    RegUserCheck phoneMatch = DonorDatabase.getInstance(this)
                            .getDonorDao()
                            .getPhone(phone);
                    if(phoneMatch!=null){
                        Toast.makeText(this, "phone number already taken", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(phone.length()<11 || phone.length()>11){
                        phoneET.setError("Phone must be 11 digit");
                        return;
                    }
                }
                RegUserCheck emailMatch = DonorDatabase.getInstance(this)
                        .getDonorDao()
                        .getEmail(email);

                if(emailMatch!=null){
                    Toast.makeText(this, "Email already taken", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            donorInfo donor_info = new donorInfo(name,age,phone,area,city,email,password,blood_group);
            DonorDatabase.getInstance(this).getDonorDao().insertDonor(donor_info);

            LayoutInflater inflater = LayoutInflater.from(this);
            View v = inflater.inflate(R.layout.reg_complete,null);
            okay = v.findViewById(R.id.okayID);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(v);
            final AlertDialog dialog = builder.create();
            dialog.show();
            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    dialog.dismiss();
                }
            });

        }

    }

    private void dismiss() {
        Intent i = new Intent(this,MainActivity.class);startActivity(i);
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
