package com.rakin.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rakin.blooddonation.DB.DonorDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView totalDonor;
    private Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalDonor = findViewById(R.id.donorCountTV);
        register = findViewById(R.id.donor);
        login = findViewById(R.id.checkbtn);
        String total_donor = DonorDatabase.getInstance(this).getDonorDao().getNumberOfRows();
        totalDonor.setText(total_donor+" "+"and counting...");


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
        Intent i= new Intent(this,About.class);
        startActivity(i);
    }

    public void register(View view) {
        Intent i = new Intent(this,Registration.class);
        startActivity(i);
    }
}
