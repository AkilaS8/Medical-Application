package com.example.youcaremedicalsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Doctor extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        chipNavigationBar = findViewById(R.id.doc_navigation);

        chipNavigationBar.setItemSelected(R.id.home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new DoctorHome()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.home:
                        fragment = new DoctorHome();
                        break;
                    case R.id.shedule:
                        fragment = new DoctorSchedule();
                        break;
                    case R.id.reports:
                        fragment = new DoctorReports();
                        break;
                    case R.id.medicine:
                        fragment = new DoctorMedicine();
                        break;
                    case R.id.chat:
                        fragment = new DoctorChat();
                        break;
                }
                if (fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}