package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class About extends AppCompatActivity {
    private Button like,follow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        like = findViewById(R.id.likeBtn);
        follow = findViewById(R.id.followBtn);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like();
            }
        });

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow();
            }
        });
    }

    private void follow() {
        Uri uri = Uri.parse("http://www.linkedin.com");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }


    private void like() {
        Uri uri = Uri.parse("http://www.facebook.com");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}
