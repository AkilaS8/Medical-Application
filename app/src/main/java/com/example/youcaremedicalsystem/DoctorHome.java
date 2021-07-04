package com.example.youcaremedicalsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;


public class DoctorHome extends Fragment {
    TextView name, age, gender, blood;
    LinearLayout diseases, allergies, conditions;
    CardView submit, nfc;
    EditText medicalID;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static DoctorHome newInstance(String param1, String param2) {
        DoctorHome fragment = new DoctorHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_doctor_home, container, false);

        name = view.findViewById(R.id.pName);
        age = view.findViewById(R.id.pAge);
        gender = view.findViewById(R.id.pGender);
        blood = view.findViewById(R.id.pBlood);
        diseases = view.findViewById(R.id.pDiseases);
        allergies = view.findViewById(R.id.pAllergies);
        conditions = view.findViewById(R.id.pConditions);
        medicalID = view.findViewById(R.id.medicalID);
        submit = view.findViewById(R.id.submit);
        nfc = view.findViewById(R.id.scanNFC);

        name.setVisibility(View.INVISIBLE);
        age.setVisibility(View.INVISIBLE);
        gender.setVisibility(View.INVISIBLE);
        blood.setVisibility(View.INVISIBLE);
        diseases.setVisibility(View.INVISIBLE);
        allergies.setVisibility(View.INVISIBLE);
        conditions.setVisibility(View.INVISIBLE);

        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NFC.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("tagOK", Context.MODE_PRIVATE);
        String val = preferences.getString("val","");

        if (val.equals("one")){
            String tag = getActivity().getIntent().getExtras().getString("tag");
            medicalID.setText(tag);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("val","two");
            editor.commit();
        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medical = medicalID.getText().toString();

                if (medical.equals("MID971463462")){
                    name.setVisibility(View.VISIBLE);
                    age.setVisibility(View.VISIBLE);
                    gender.setVisibility(View.VISIBLE);
                    blood.setVisibility(View.VISIBLE);
                    diseases.setVisibility(View.VISIBLE);
                    allergies.setVisibility(View.VISIBLE);
                    conditions.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}