package dev.lytran.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.lytran.recylerviewexample.R;
import dev.lytran.model.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    Activity context;
    ArrayList<Beer> products;

    public BeerAdapter(Activity context, ArrayList<Beer> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View customView = inflater.inflate(R.layout.custom_item, parent, false);

        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imvThumb.setImageResource(products.get(position).getProductThumb());
        holder.txtInfo.setText(products.get(position).getProductName() + "-" + products.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView txtInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Link views
            imvThumb  = itemView.findViewById(R.id.imvBeer);
            txtInfo = itemView.findViewById(R.id.txtBeerName);
        }
    }
}
