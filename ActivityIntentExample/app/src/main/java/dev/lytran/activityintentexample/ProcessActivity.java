package dev.lytran.activityintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProcessActivity extends AppCompatActivity {
    TextView txtNumber;
    Button btnProcess;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        linkViews();
        addEvents();
        getData();
    }

    private void addEvents() {
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(txtNumber.getText().toString());
                intent.putExtra("power", number * number);
                // Ko start intent
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void linkViews() {
        txtNumber = findViewById(R.id.txtNumber);
        btnProcess = findViewById(R.id.btnProcess);
    }

    private void getData() {
        intent = getIntent();
        txtNumber.setText(intent.getStringExtra("number"));
    }
}