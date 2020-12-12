package com.teamone.salesmanagement.Bill;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamone.salesmanagement.Product.Product;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.BillDAO;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    List<Bill> billList;
    BillDAO billDAO;

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBillId = itemView.findViewById(R.id.tvBillId);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
