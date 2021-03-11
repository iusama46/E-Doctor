package com.faysol.ehomevisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NoInternet extends AppCompatActivity {
    Button btnretry;
    ImageView nointernetlogo;
    TextView nointernettxt;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        btnretry = findViewById(R.id.btnretry);
        nointernettxt = findViewById(R.id.nointernettxt);
        nointernetlogo = findViewById(R.id.nointernetlogo);
        relativeLayout = findViewById(R.id.nointernetlayout);
        btnretry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoInternet.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}