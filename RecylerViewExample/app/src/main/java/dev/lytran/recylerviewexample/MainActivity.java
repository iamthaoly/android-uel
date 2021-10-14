package dev.lytran.recylerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;

import dev.lytran.adapter.BeerAdapter;
import dev.lytran.model.Beer;

public class MainActivity extends AppCompatActivity {

    ArrayList<Beer> beers;
    RecyclerView rcvBeer;
    BeerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();
        configRecylerView();
        initData();
        configAdapter();
    }

    private void linkViews() {
        rcvBeer = findViewById(R.id.rcvProduct);
    }

    private void configRecylerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rcvBeer.setLayoutManager(manager);

//        DividerItemDecoration decoration = new DividerItemDecoration(this, manager.getOrientation());
        // Custom divider
        DividerItemDecoration customDivider = new DividerItemDecoration(rcvBeer.getContext(), manager.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
        customDivider.setDrawable(drawable);

        rcvBeer.addItemDecoration(customDivider);

        rcvBeer.setHasFixedSize(true);

        // Set animation
        rcvBeer.setItemAnimator((new DefaultItemAnimator()));
    }

    private void initData() {
        beers = new ArrayList<Beer>();
        beers.add(new Beer(R.drawable.beer333, "Beer 333", 19000));
        beers.add(new Beer(R.drawable.hanoi, "Hanoi 90", 19000));
        beers.add(new Beer(R.drawable.tiger, "Bia con ho", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer 333", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer 333", 19000));
        beers.add(new Beer(R.drawable.hanoi, "Hnoi VN", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer 333", 19000));
        beers.add(new Beer(R.drawable.tiger, "Tiger 333", 19000));
    }

    private void configAdapter() {
        adapter = new BeerAdapter(MainActivity.this, beers);
        rcvBeer.setAdapter(adapter);
    }
}