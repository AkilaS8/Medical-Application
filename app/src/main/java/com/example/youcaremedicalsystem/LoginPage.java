package com.example.youcaremedicalsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {
    CardView doctor, nurse, attendant, lab, pharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        doctor = findViewById(R.id.card_view_doctor);
        nurse = findViewById(R.id.card_view_nurse);
        attendant = findViewById(R.id.card_view_attendant);
        lab = findViewById(R.id.card_view_lab);
        pharmacy = findViewById(R.id.card_view_pharmacy);

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginPage.this,Doctor.class);
                startActivity(intent);
            }
        });

        attendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginPage.this,Attendant.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}