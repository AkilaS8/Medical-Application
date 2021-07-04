package com.example.youcaremedicalsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Attendant extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        chipNavigationBar = findViewById(R.id.doc_navigation);

        chipNavigationBar.setItemSelected(R.id.scan,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new AScan()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.scan:
                        fragment = new AScan();
                        break;
                    case R.id.details:
                        fragment = new ADetails();
                        break;
                    case R.id.notifications:
                        fragment = new ANotification();
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