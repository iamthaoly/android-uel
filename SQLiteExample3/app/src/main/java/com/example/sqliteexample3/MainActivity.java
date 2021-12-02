package com.example.sqliteexample3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtDes;
    Button btnCapture, btnSave, btnCancel;
    ImageView imvPhoto;

    BottomSheetDialog sheetDialog;

    LinearLayout sheetCamera, sheetGallery;
    boolean isCamera;
    MyDatabase db = new MyDatabase(MainActivity.this);

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        createSheetDialog();
        addEvents();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    if (isCamera) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imvPhoto.setImageBitmap(bitmap);
                    }
                    else {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                imvPhoto.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
            }
        });
    }

    private void addEvents() {
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open bottom sheet
                sheetDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, des;
                name = edtName.getText().toString();
                des = edtDes.getText().toString();

                if (!name.equals("") && !des.equals("")) {
                    boolean flag = db.insertData(name, des, convertPhoto());
                    if (flag) {
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Fail!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private byte[] convertPhoto() {
        BitmapDrawable drawable = (BitmapDrawable) imvPhoto.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    private void createSheetDialog() {
        if (sheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
            sheetCamera = view.findViewById(R.id.sheetOpenCamera);
            sheetGallery  = view.findViewById(R.id.sheetOpenGallery);
            sheetCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open camera
                    isCamera = true;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();

                }
            });
            sheetGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open gallery
                    isCamera = false;
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
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