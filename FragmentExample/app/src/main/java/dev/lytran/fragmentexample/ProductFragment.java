package dev.lytran.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import dev.lytran.adapter.ProductAdapter;
import dev.lytran.model.MyItemClick;
import dev.lytran.model.Product;

public class ProductFragment extends Fragment {

    ListView lvProduct ;
    ArrayList<Product> products;
    ProductAdapter adapter;


    MyItemClick itemClick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment, container, false);
        lvProduct = v.findViewById(R.id.lvProduct);
        adapter = new ProductAdapter(getContext(), R.layout.item_listview, initData());
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClick = new MyItemClick() {
                    @Override
                    public void click(Product p) {
                        itemClick = (MyItemClick) getActivity();
                        if (itemClick != null) {
                            itemClick.click(products.get(i));
                        }
                    }
                };
            }
        });
        return v;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();
        products.add(new Product("Heineken", 19003 , R.drawable.sapporo, "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. "));
        products.add(new Product("Hanoi", 19003 , R.drawable.hanoi, "2 Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. " ));
        products.add(new Product("Sing", 19003 , R.drawable.beer333, "3 Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. "));
        products.add(new Product("Ken", 19003 , R.drawable.larue, "55 Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. "));
        products.add(new Product("Tiger", 19003 , R.drawable.sapporo, "12 Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. "));
        products.add(new Product("Heineken", 19003 , R.drawable.saigon, "L   orem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. "));

        return  products;
    }
}
