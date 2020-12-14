package com.teamone.salesmanagement.Product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamone.salesmanagement.Bill.AddBillActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private List<Product> productListSort;
    private OnItemClickListener listener;
    private ProductDAO dao;
    private Filter PFilter;
    CustomFilter customFilter;
    @Override
    public int getItemCount() {
        return productList.size();
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
            if(constraint!=null || constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Product> filter = new ArrayList<>();
                for (int i=0; i<productListSort.size();i++){
                    if(productListSort.get(i).getProductName().toUpperCase().contains(constraint)){
                        Product product1 = new Product(productListSort.get(i).getProductImage(),
                                productListSort.get(i).getProductCode(),
                                productListSort.get(i).getProductName(),
                                productListSort.get(i).getProductPrice(),
                                productListSort.get(i).getProductSize());
                        filter.add(product1);
                    }
                }
                results.count = filter.size();
                results.values=filter;
            }else {
                results.count = productListSort.size();
                results.values=productListSort;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           productList = (ArrayList<Product>) results.values;
           notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
        this.productListSort = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_san_pham, parent, false);
        dao = new ProductDAO(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.imgProduct.setImageBitmap(
                getImage(productList.get(position).getProductImage()));
        holder.tvName.setText(productList.get(position).getProductName());
        holder.tvSize.setText("Size: " + productList.get(position).getProductSize());
        holder.tvPrice.setText("Giá: " + productList.get(position).getProductPrice() + " VND");
        holder.imageButtonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddBillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("code", productList.get(position).getProductCode());
                bundle.putByteArray("image", productList.get(position).getProductImage());
                bundle.putString("name", productList.get(position).getProductName());
                bundle.putDouble("price", productList.get(position).getProductPrice());
                bundle.putString("size", productList.get(position).getProductSize());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("code", productList.get(position).getProductCode());
                bundle.putByteArray("image", productList.get(position).getProductImage());
                bundle.putString("name", productList.get(position).getProductName());
                bundle.putDouble("price", productList.get(position).getProductPrice());
                bundle.putString("size", productList.get(position).getProductSize());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvSize;
        ImageButton imageButtonArrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvSize = itemView.findViewById(R.id.tvSize);
            imageButtonArrow = itemView.findViewById(R.id.imageButtonArrow);

        }

    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
