package dev.lytran.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.lytran.recylerviewexample.R;
import dev.lytran.model.Beer;

public class BeerAdapter extends BaseAdapter {
    Activity context;
    List<Beer> arrBeer;
    int custom_item;

    public BeerAdapter(Activity context, int item_grid, List<Beer> arrBeer) {
        this.context = context;
        this.arrBeer = arrBeer;
        this.custom_item = item_grid;
    }

    @Override
    public int getCount() {
        return arrBeer.size();
    }

    @Override
    public Object getItem(int i) {
        return arrBeer.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(custom_item, null);
            viewHolder.imvBeer = view.findViewById(R.id.imvBeer);
            viewHolder.txtName = view.findViewById(R.id.txtBeerName);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Beer b = arrBeer.get(i);
        viewHolder.imvBeer.setImageResource(b.getProductThumb());
        viewHolder.txtName.setText(b.getProductName());

        return view;
    }

    private static class ViewHolder {
        ImageView imvBeer;
        TextView txtName;
    }
}
