package com.teamone.salesmanagement.Bill;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.teamone.salesmanagement.Customer.AddCustomerActivity;
import com.teamone.salesmanagement.Customer.Customer;
import com.teamone.salesmanagement.Product.ListProductActivity;
import com.teamone.salesmanagement.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AddBillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText editTextNgayTaoDon;
    private Spinner spinnerCustomer;
    private List<Customer> customersList;
    private List<String> customerNameList;
    private Toolbar toolbar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        toolbar = findViewById(R.id.toolbaraddbill);
        setSupportActionBar(toolbar);
        editTextNgayTaoDon = findViewById(R.id.edNgayTaoDOn);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        customersList = new ArrayList();
        customerNameList = new ArrayList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Get customer from spinner
        getCustomer();
    }


    public void addHoaDon(View view) {
    }

    public void getCustomer() {
//        customersList.add(new Customer("Khách hàng 01", "01/05/2001", "Hải Dương", "0395436290"));
//        customersList.add(new Customer("Khách hàng 02", "01/05/2001", "Hải Dương", "0395436290"));
//        customersList.add(new Customer("Khách hàng 03", "01/05/2001", "Hải Dương", "0395436290"));
        for (Customer customer: customersList) {
            customerNameList.add(customer.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, customerNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomer.setAdapter(adapter);

//        // When user select a List-Item.
//        this.spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Adapter adapter = parent.getAdapter();
//                Customer customer = (Customer) adapter.getItem(position);
//
//                Toast.makeText(getApplicationContext(), "Selected Customer: " + customer.getName() ,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void ngayTaoDon(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {
        editTextNgayTaoDon.setText(sdf.format(calendar.getTime()));
    }

    public void taoHoaDon(View view) {
    }

    public void themSPMua(View view) {
        Intent intent = new Intent(AddBillActivity.this, ListProductActivity.class);
        startActivity(intent);
    }

    public void themKH(View view) {
        Intent intent = new Intent(AddBillActivity.this, AddCustomerActivity.class);
        startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}