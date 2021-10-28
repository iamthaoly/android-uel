package dev.lytran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.lytran.fragmentexample.R;
import dev.lytran.model.Product;

public class ProductAdapter extends BaseAdapter {
    Context context;
    int item_layout;
    ArrayList<Product> products;

    public ProductAdapter(Context context, int item_layout, ArrayList<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(item_layout, null);
            holder.imvProductThumb = view.findViewById(R.id.imvProductThumbnail);
            holder.txtName = view.findViewById(R.id.txtProductName);
            holder.txtPrice = view.findViewById(R.id.txtProductPrice);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        // Binding data
        Product p = products.get(i);
        holder.imvProductThumb.setImageResource(p.getProductThumbnail());
        holder.txtName.setText(p.getProductName());
        holder.txtPrice.setText(String.valueOf(p.getProductPrice()));
        return view;
    }

    private static class ViewHolder {
        ImageView imvProductThumb;
        TextView txtName, txtPrice;
    }
}
