package dev.lytran.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import dev.lytran.recylerviewexample.R;
import dev.lytran.model.Beer;

public class BeerAdapter extends RecyclerView.Adapter {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
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
