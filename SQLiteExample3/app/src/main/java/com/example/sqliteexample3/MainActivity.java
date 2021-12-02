package com.example.sqliteexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtDes;
    Button btnCapture, btnSave, btnCancel;
    ImageView imvPhoto;

    BottomSheetDialog sheetDialog;

    LinearLayout sheetCamera, sheetGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews() ;
        addEvents();
    }

    private void addEvents() {
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open bottom sheet
                openSheetDialog();
            }
        });
    }

    private void openSheetDialog() {
        if (sheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
            sheetCamera = view.findViewById(R.id.sheetOpenCamera);
            sheetGallery  = view.findViewById(R.id.sheetOpenGallery);
            sheetCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open camera

                }
            });
            sheetGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open gallery
                }
            });
            sheetDialog = new BottomSheetDialog(this);
            sheetDialog.setContentView(view);


        }
    }

    private void linkViews() {
        edtName = findViewById(R.id.edtName);
        edtDes = findViewById(R.id.edtDescription);
        btnCapture = findViewById(R.id.btnCapturePhoto);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        imvPhoto = findViewById(R.id.imvPhoto);
    }
}