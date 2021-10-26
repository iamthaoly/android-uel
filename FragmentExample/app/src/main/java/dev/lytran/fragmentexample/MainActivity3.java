package dev.lytran.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    EditText edtContent;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment3 fragment3 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3);
                if (fragment3 != null) {
                    fragment3.setContent(edtContent.getText().toString());
//                    fragment3.edtFrag3.setText(edtContent.getText().toString());
                }
            }
        });
    }

    private void linkViews() {
        edtContent = findViewById(R.id.edtContent);
        btnChange = findViewById(R.id.btnChange);
    }

}