package com.teamone.salesmanagement.Customer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamone.salesmanagement.Product.Product;
import com.teamone.salesmanagement.Product.ProductAdapter;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    Context context;
    List<Customer> customerList;
    List<Customer> customerListSort;
    CustomerDAO dao;
    private LayoutInflater inflater;
CustomFilter customFilter;
    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
        this.inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dao = new CustomerDAO(context);
        this.customerListSort = customerList;
    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int i) {
        return customerList.get(i);
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
            view = inflater.inflate(R.layout.item_list_khach_hang,null);
            holder.name = (TextView)view.findViewById(R.id.tvTenKH);
            holder.phone = (TextView)view.findViewById(R.id.tvSDT);
            holder.dateOfBirth = (TextView)view.findViewById(R.id.tvNgaySinh);
            holder.img = (ImageView)view.findViewById(R.id.ivDetailKH);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext() ;
                    Intent intent = new Intent(context,EditCustomerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",customerList.get(i).getIdCustomer());
                    bundle.putString("name",customerList.get(i).getName());
                    bundle.putString("phone",customerList.get(i).getPhone());
                    bundle.putString("dateOfBirth",customerList.get(i).getDateOfBirth());
                    bundle.putString("address",customerList.get(i).getAddress());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        Customer customer = customerList.get(i);
        holder.name.setText(customer.getName());
        holder.phone.setText(customer.getPhone());
        holder.dateOfBirth.setText(customer.getDateOfBirth());
        return view;
    }

    public void changeDataset(List<Customer> ls) {
        this.customerList = ls;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ViewHolder{
        TextView name,phone,dateOfBirth;
        ImageView img;
    }

    public Filter getFilter() {
        if (customFilter == null){
            customFilter = new CustomFilter();
        }
        return customFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint==null || constraint.length()==0){
                results.values = customerListSort;
                results.count=customerListSort.size();
            }else {
                List<Customer> customers = new ArrayList<>();
                for (Customer p: customerList){
                    if(p.getPhone().toUpperCase().startsWith(constraint.toString().toUpperCase()))customers.add(p);
                }
                results.values=customers;
                results.count=customers.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetChanged();
            }else {
                customerList=(List<Customer>)results.values;
                notifyDataSetChanged();
            }
        }
    }
    public void resetData1(){
        customerList=customerListSort;
    }
}