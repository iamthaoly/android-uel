package dev.lytran.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import dev.lytran.model.MyItemClick;
import dev.lytran.model.Product;
import dev.lytran.utils.Constant;

public class ProductActivity extends AppCompatActivity implements MyItemClick {

    private FragmentManager manager;
//    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layoutContainer, new ProductFragment());
        transaction.commit();
    }

    @Override
    public void click(Product p) {
        FragmentTransaction transaction = manager.beginTransaction();
        ProductInfoFragment infoFragment = new ProductInfoFragment();

        //1st way send data between fragment
//        infoFragment.getProduct(p);

        // 2nd way
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.SELECTED_ITEM, p);
        infoFragment.setArguments(bundle);

        transaction.replace(R.id.layoutContainer, infoFragment);
        // de back ve fragment cu
        transaction.addToBackStack(null);
        transaction.commit();
    }
}