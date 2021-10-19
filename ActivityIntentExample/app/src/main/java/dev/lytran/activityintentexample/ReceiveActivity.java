package dev.lytran.activityintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import dev.lytran.model.Product1;
import dev.lytran.model.Product2;

public class ReceiveActivity extends AppCompatActivity {

    TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        linkViews();
        getData();
    }

    private void linkViews() {
        txtContent = findViewById(R.id.txtContent);
    }

    private void getData() {
        Intent intent = getIntent();

        // 1st way
//        int num = intent.getIntExtra("Number", 0);
//        double score  = intent.getDoubleExtra("score", 0.0);
//        boolean yess = intent.getBooleanExtra("Yes", false);
//        String str = intent.getStringExtra("text");
        Bundle bundle = intent.getBundleExtra("bundle");
        int num = bundle.getInt("num");
        boolean yess = bundle.getBoolean("Yes", false);
        String str = bundle.getString("text");

        txtContent.append("Num: " + num + "\n");
//        txtContent.append("Score: " + score + "\n");
        txtContent.append("Bool: " + yess + "\n");
        txtContent.append("String: " + str + "\n");

        Product2 p2 = bundle.getParcelable("info");

//        Product1 p = (Product1) intent.getSerializableExtra("productInfo");
//        Product2 p2 = intent.getParcelableExtra("product2");

        //        txtContent.append("Product: " + p + "\n");
        txtContent.append("Product2: " + p2 + "\n");

    }
}