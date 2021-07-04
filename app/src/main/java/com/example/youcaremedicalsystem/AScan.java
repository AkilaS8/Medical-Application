package com.example.youcaremedicalsystem;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AScan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AScan extends Fragment {
    CodeScanner codeScanner;
    CodeScannerView scannview;
    TextView resultData;
    Dialog dialog;
    TextView PopUpText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AScan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AScan.
     */
    // TODO: Rename and change types and number of parameters
    public static AScan newInstance(String param1, String param2) {
        AScan fragment = new AScan();
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
        View view = inflater.inflate(R.layout.fragment_a_scan, container, false);
        scannview =(CodeScannerView) view.findViewById(R.id.ScannerView);
        codeScanner =(CodeScanner) new CodeScanner(getActivity(),scannview);

        scanner();

        return  view;
    }

    public void scanner()
    {
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  Toast.makeText(getActivity(), ""+result, Toast.LENGTH_SHORT).show();
                        dialogbox(result.getText());
                        // resultData.setText(result.getText());
                    }
                });
            }
        });

        scannview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    public void dialogbox(String text)
    {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.setCancelable(false);
        Button btnok = dialog.findViewById(R.id.btnok);
        PopUpText= dialog.findViewById(R.id.textView2);
        PopUpText.setText("Appointment No: "+ text);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Appoinment Added Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void requestForCamera() {
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(getActivity(), "Camera Permission is Required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestForCamera();
    }
}