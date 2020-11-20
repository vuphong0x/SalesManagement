package com.teamone.salesmanagement.Bill;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.teamone.salesmanagement.Customer.Addcustomer;
import com.teamone.salesmanagement.Product.ListProduct;
import com.teamone.salesmanagement.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddBill extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
EditText editTextNgayTaoDon;
    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        editTextNgayTaoDon=findViewById(R.id.edNgayTaoDOn);
    }


    public void addHoaDon(View view) {
    }

    public void ngayTaoDon(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
    private void setDate(final Calendar calendar){
        editTextNgayTaoDon.setText(sdf.format(calendar.getTime()));
    }

    public void taoHoaDon(View view) {
    }

    public void themSPMua(View view) {
        Intent intent = new Intent(AddBill.this, ListProduct.class);
        startActivity(intent);
    }

    public void themKH(View view) {
        Intent intent = new Intent(AddBill.this, Addcustomer.class);
        startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
        }
    }
}