package dev.lytran.activityintentexample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dev.lytran.model.Product1;
import dev.lytran.model.Product2;

public class MainActivity extends AppCompatActivity {

    Button btnOpenActivity2, btnOpenDialog, btnSendData, btnSend, btnCall, btnDial;
    EditText edtNumber, edtPhoneNumber;
    TextView txtResult;

    ImageView imvPhoto;
    Button btnOpenCamera;
//    public static final int REQUEST_CODE = 1;

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main", "onCreate!");

        linkViews();
        addEvents();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                int number = result.getData().getIntExtra("power", -1);
                if (number != -1) {
                    txtResult.setText(String.valueOf(result.getData().getIntExtra("power", 0)));
                }
                // get image from camera
                Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                if (bitmap != null) {
                    imvPhoto.setImageBitmap(bitmap);
                }
            }

        });
    }


    private void linkViews() {
        btnOpenActivity2 = findViewById(R.id.btnOpenActivity2);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnSendData = findViewById(R.id.btnSendData);
        btnSend = findViewById(R.id.btnSend);
        edtNumber = findViewById(R.id.edtNumber);
        txtResult = findViewById(R.id.txtResult);

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnDial = findViewById(R.id.btnDial);
        btnCall = findViewById(R.id.btnCall);

        btnOpenCamera = findViewById(R.id.btnOpenCamera);
        imvPhoto = findViewById(R.id.imvPhoto);
    }

    private void addEvents() {
        btnOpenActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Activity 2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                // 1st way
//                intent.putExtra("Number", 9);
//                intent.putExtra("score", 8.9);
//                intent.putExtra("Yes", true);
//                intent.putExtra("text", "Heck?");
//
//                Product1 p1 = new Product1(29, "Heineken", 19.5);
//                intent.putExtra("productInfo", p1);
//
//                Product2 p2 = new Product2();
//                p2.setProductCode(88);
//                p2.setProductName("Tigur");
//                p2.setProductPrice(15);
//                intent.putExtra("product2", p2);

                // 2nd way - Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("Number", 10);
                bundle.putDouble("score", 5.4);
                bundle.putBoolean("Yes", true);
                bundle.putString("text", "Ohmm");

                Product1 p3 = new Product1(71, "lion", 8);
                Product2 p4 = new Product2();
                p4.setProductCode(70);
                p4.setProductName("Tankr");
                p4.setProductPrice(21);

                bundle.putParcelable("info", p4);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProcessActivity.class);
                // Attach data

                intent.putExtra("number", edtNumber.getText().toString());
                // 1st way
//                startActivityForResult(intent, REQUEST_CODE);
                activityResultLauncher.launch(intent);

            }
        });
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + edtPhoneNumber.getText().toString());
                intent.setData(uri);
                startActivity(intent);

            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + edtPhoneNumber.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
            }
        });
    }

// 1st way
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            txtResult.setText(String.valueOf(data.getIntExtra("power", 0)));
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main", "onStart!");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main", "onResume!");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Main", "onPause!");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main", "onStop!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main", "onDestroy!");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Main", "onRestart!");

    }
}