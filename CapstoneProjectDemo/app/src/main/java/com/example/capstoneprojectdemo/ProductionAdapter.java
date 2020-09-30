package com.example.capstoneprojectdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductionAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Production> productionList;

    public ProductionAdapter(Context context, int layout, List<Production> productionList) {
        this.context = context;
        this.layout = layout;
        this.productionList = productionList;
    }

    private class ViewHolder {
        TextView tvNameProduct, tvPriceProduct, tvQuantity, tvNameSeller;
        ImageView ivProduct;
    }

    @Override
    public int getCount() {
        return productionList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvNameProduct = (TextView) convertView.findViewById(R.id.tvNameProductDP);
            holder.tvNameSeller = (TextView) convertView.findViewById(R.id.tvNameSellerDP);
            holder.tvPriceProduct = convertView.findViewById(R.id.tvPriceProductDP);
            holder.tvQuantity = convertView.findViewById(R.id.tvQuantityDP);
            holder.ivProduct = convertView.findViewById(R.id.ivProductDP);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Production production = productionList.get(position);

        holder.tvNameProduct.setText(production.getId() + "");
        holder.tvNameSeller.setText(production.getSallerid() + "");
        holder.tvQuantity.setText("Left: " + production.getSelled_quantity() + "/" + production.getQuantity());
        holder.tvPriceProduct.setText("Price: $" + production.getPrice());
        holder.ivProduct.setImageResource(R.mipmap.sample_food_foreground);
        return convertView;

    }
}
