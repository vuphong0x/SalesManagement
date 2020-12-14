package com.teamone.salesmanagement.Bill;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamone.salesmanagement.Product.Product;
import com.teamone.salesmanagement.Product.ProductAdapter;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.BillDAO;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private static final String TAG = "AAA";
    List<Bill> billList;
    List<Bill> billListsort;
    BillDAO billDAO;
    CustomFilter customFilter;

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
        this.billListsort=billList;
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
                ArrayList<Bill> filter = new ArrayList<>();
                for (int i=0; i<billListsort.size();i++){
                    if(billListsort.get(i).getMaHoaDon().toUpperCase().contains(constraint)||billListsort.get(i).getDate().toUpperCase().contains(constraint)){
                        Bill bill1 = new Bill(billListsort.get(i).getMaHoaDon(),
                                billListsort.get(i).getTenKhachHang(),
                                billListsort.get(i).getTongTien(),
                                billListsort.get(i).getDate());
                        filter.add(bill1);
                    }
                }
                results.count = filter.size();
                results.values=filter;
            }else {
                results.count = billListsort.size();
                results.values=billListsort;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            billList = (ArrayList<Bill>) results.values;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_hoa_don, parent, false);
        billDAO = new BillDAO(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        holder.tvBillId.setText("Mã hóa đơn: " + billList.get(position).getMaHoaDon());
        holder.tvCustomerName.setText("Khách hàng: " + billList.get(position).getTenKhachHang());
        holder.tvTotalMoney.setText("Tổng tiền:" + billList.get(position).getTongTien() + " VND");
        holder.tvNgay.setText("Ngày mua: " +billList.get(position).getDate());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billDAO.deleteBill(billList.get(position).getMaHoaDon());
                billList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Dialog dialog = new Dialog(v.getContext());
//                dialog.setContentView(R.layout.dialog_bill_detail);
//                TextView customerName = dialog.findViewById(R.id.tvCustomerName);
//                TextView date = dialog.findViewById(R.id.tvDate);
//                TextView totalMoney = dialog.findViewById(R.id.tvTotalMoney);
//                RecyclerView rvProduct = dialog.findViewById(R.id.rvProduct);
//
//
//                dialog.show();
                Log.d(TAG, "onClick: " + billDAO.getAllBill().get(position).getDate());
            }
        });
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBillId, tvCustomerName, tvTotalMoney;
        ImageButton btnDelete;
        private TextView tvNgay;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBillId = itemView.findViewById(R.id.tvBillId);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            tvNgay = itemView.findViewById(R.id.tv_ngay);
        }
    }

}
