package dev.lytran.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.lytran.model.Product;
import dev.lytran.utils.Constant;

public class ProductInfoFragment extends Fragment {
    Product product = null;
    TextView txtName, txtPrice, txtSlogan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_info_fragment, container, false);
        txtName = v.findViewById(R.id.txtName);
        txtPrice = v.findViewById(R.id.txtPrice);
        txtSlogan = v.findViewById(R.id.txtSlogan);

        Bundle bundle = getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            txtName.setText(product.getProductName());
            txtPrice.setText(String.valueOf(product.getProductPrice()));
            txtSlogan.setText(product.getProductSlogan());
        }
//        if (product != null) {
//            txtName.setText(product.getProductName());
//            txtPrice.setText(String.valueOf(product.getProductPrice()));
//            txtSlogan.setText(product.getProductSlogan());
//        }
        return  v;
    }

//    public void getProduct(Product p) {
//        this.product = p;
//    }
}
