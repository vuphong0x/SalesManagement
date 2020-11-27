package com.teamone.salesmanagement.Product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamone.salesmanagement.Customer.Customer;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context context;
    List<Product> productList;
    ProductDAO dao;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dao = new ProductDAO(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_list_san_pham,null);
            holder.name = (TextView)view.findViewById(R.id.tvTenSP);
            holder.price = (TextView)view.findViewById(R.id.tvPrice);
            holder.address= (TextView)view.findViewById(R.id.tvAddressSP);
            holder.img = (ImageView)view.findViewById(R.id.ivItemSP);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context,EditProduct.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",productList.get(i).getId());
                    bundle.putString("image",String.valueOf(productList.get(i).getImg()));
                    bundle.putString("name",productList.get(i).getName());
                    bundle.putString("price",productList.get(i).getPrice());
                    bundle.putString("address",productList.get(i).getAddress());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        Product product = productList.get(i);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.address.setText(product.getAddress());
        return view;
    }
    public  static  class ViewHolder{
        TextView name,price,address;
        ImageView img;
    }
    public void changeDataset(List<Product> ls) {
        this.productList = ls;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
