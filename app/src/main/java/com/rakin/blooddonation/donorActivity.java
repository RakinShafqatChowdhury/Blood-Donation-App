package com.rakin.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rakin.blooddonation.DB.DonorDatabase;
import com.rakin.blooddonation.Entities.donorInfo;

public class donorActivity extends AppCompatActivity {
private TextView totalDonor,userName;
private Button yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        totalDonor = findViewById(R.id.donorCountTV);

        String total_donor = DonorDatabase.getInstance(this).getDonorDao().getNumberOfRows();
        totalDonor.setText(total_donor+" "+"and counting...");

        userName = findViewById(R.id.userNameID);
        Bundle bundle = getIntent().getExtras();

        //String message = bundle.getString("message");
        String email = bundle.getString("email");
        donorInfo donor  = DonorDatabase.getInstance(this).getDonorDao().getDonorInfo(email);
        //String userbg = DonorDatabase.getInstance(this).getDonorDao().getUserBloodGroup(message);

        String blood = bundle.getString("bloodgrp");

        //userName.setText("Welcome!"+" "+message+"\n"+"Blood Group: "+blood);
        userName.setText("Welcome!"+" "+donor.getDonor_name()+"\n"+"Blood Group: "+blood);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
        inflater.inflate(R.menu.share_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.share_menu){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sub = "Become a Blood Donor now!!";
            String body = "Join our bloody finder community and be the saviour for the society.\n Link: google.playstore.com/bloodyfinder";

            i.putExtra(Intent.EXTRA_SUBJECT,sub);
            i.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(i,"Share this app with: "));

        }
        switch (item.getItemId()) {
            case R.id.setting_item:
                break;
            case R.id.update_item:
                Toast.makeText(this, "Checking for update...", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Up to date", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login_page(View view) {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    public void blood_need(View view) {
        Intent i = new Intent(this,BloodSearch.class);
        startActivity(i);
    }

    public void emergency_help(View view) {
        Intent i = new Intent(this,Emergency.class);
        startActivity(i);
    }

    public void blood_info(View view) {
        Intent i = new Intent(this,BloodInfo.class);
        startActivity(i);
    }

    public void about_info(View view) {
    }

    public void register(View view) {
        Intent i = new Intent(this,Registration.class);
        startActivity(i);
    }

    public void logoutUser(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.logout, null);
        yes = v.findViewById(R.id.yesBtn);
        no = v.findViewById(R.id.noBtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout_user();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }

    private void logout_user() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void updateProfile(View view) {
        Bundle bundle = getIntent().getExtras();
        String message1 = bundle.getString("email");

        Toast.makeText(this, message1, Toast.LENGTH_SHORT).show();
        donorInfo donorInfo = DonorDatabase.getInstance(this).getDonorDao().getDonorInfo(message1);
        Intent i = new Intent(this,Update_Donor.class);
        i.putExtra("donorInfo", donorInfo);
        startActivity(i);

    }

}
