package dev.lytran.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btnFrag1, btnFrag2;
//    FragmentManager manager ;
//    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        linkViews();
        addEvents();

//        manager = getSupportFragmentManager();
    }

    private void linkViews() {
        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);

    }
    private void addEvents() {
//        btnFrag1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transaction = manager.beginTransaction();
//                Fragment1 fragment1 = new Fragment1();
//                transaction.add(R.id.layoutContainer, fragment1);
//                transaction.commit();
//            }
//        });
//        btnFrag2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transaction = manager.beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
//                transaction.replace(R.id.layoutContainer, fragment2);
//                transaction.commit();
//            }
//        });
        btnFrag1.setOnClickListener(myClick);
        btnFrag2.setOnClickListener(myClick);
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = null;
            if (view.getId() == R.id.btnFrag1) {
                fragment = new Fragment1();
            }
            else if (view.getId() == R.id.btnFrag2) {
                fragment = new Fragment2();
                Bundle bundle =  new Bundle();
                bundle.putString("str", "abc123");
                fragment.setArguments(bundle);
            }
            transaction.replace(R.id.layoutContainer, fragment);
            transaction.commit();
        }
    };
}