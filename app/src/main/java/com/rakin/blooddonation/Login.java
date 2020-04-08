package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rakin.blooddonation.DB.DonorDatabase;
import com.rakin.blooddonation.Entities.donorInfo;
import com.rakin.blooddonation.pojos.UserEmailPassword;

import java.util.List;

public class Login extends AppCompatActivity {
    private EditText emailET,passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passwordLogin);

    }

    public void login(View view) {
        String email = emailET.getText().toString();
        String password = passET.getText().toString();

        UserEmailPassword emailPasswordObj = DonorDatabase.getInstance(this)
                .getDonorDao()
                .getEmailAndPassword(email, password);
        //String total_donor = DonorDatabase.getInstance(this).getDonorDao().getNumberOfRows();
       String username = DonorDatabase.getInstance(this).getDonorDao().getUsername(email);
       String blood_grp = DonorDatabase.getInstance(this).getDonorDao().getUserBloodGroup(email);
        if(emailPasswordObj != null){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,donorActivity.class);

            intent.putExtra("message", username);
            intent.putExtra("email", email);
            intent.putExtra("bloodgrp", blood_grp);

            startActivity(intent);
            finish();

        }
        else{
            Toast.makeText(this, "Wrong Username and/or Password", Toast.LENGTH_SHORT).show();
        }

    }
}
