package dev.lytran.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import dev.lytran.adapter.ProductAdapter;
import dev.lytran.model.Product;

public class ProductFragment extends Fragment {

    ListView lvProduct ;
    ArrayList<Product> products;
    ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment, container, false);
        lvProduct = v.findViewById(R.id.lvProduct);
        adapter = new ProductAdapter(getContext(), R.layout.item_listview, initData());
        lvProduct.setAdapter(adapter);
        return v;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();
        products.add(new Product("Heineken", 19003 , R.drawable.sapporo));
        products.add(new Product("Hanoi", 19003 , R.drawable.hanoi));
        products.add(new Product("Sing", 19003 , R.drawable.beer333));
        products.add(new Product("Ken", 19003 , R.drawable.larue));
        products.add(new Product("Tiger", 19003 , R.drawable.sapporo));
        products.add(new Product("Heineken", 19003 , R.drawable.saigon));

        return  products;
    }
}
