package com.teamone.salesmanagement.Product;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.teamone.salesmanagement.Bill.AddBillActivity;
import com.teamone.salesmanagement.Bill.ListBillActivity;
import com.teamone.salesmanagement.MainActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.ProductDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgProduct;
    private EditText edtProductCode, edtProductName, edtProductPrice, edtProductSize;
    private ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgProduct = findViewById(R.id.addAnhSanPham);
        edtProductCode = findViewById(R.id.edtProductCode);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductSize = findViewById(R.id.edtProductSize);

    }

    public void addProductPicture(View view) {
        Dialog dialog = new Dialog(AddProductActivity.this);
        dialog.setContentView(R.layout.themanh);
        Button button1 = dialog.findViewById(R.id.btnCamera);
        Button button2 = dialog.findViewById(R.id.btnStorage);
        dialog.show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddProductActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 888);
                } else {
                    ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{Manifest.permission.CAMERA}, 999);
                }
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1);
                dialog.dismiss();
            }
        });
    }


    public void addProduct(View view) {
        dao = new ProductDAO(this);

        try {
            // Get image from ImageView
            imgProduct.invalidate();
            BitmapDrawable drawable = (BitmapDrawable) imgProduct.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            // convert bitmap to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Log.d("AAA", "addProduct: " + byteArray);
//            if (bitmap != null && !bitmap.isRecycled()) {
//                bitmap.recycle();
//                bitmap = null;
//            }


            // get data from EditText
            String productCode = edtProductCode.getText().toString().trim();
            String productName = edtProductName.getText().toString().trim();
            double productPrice = Double.parseDouble(edtProductPrice.getText().toString().trim());
            String productSize = edtProductSize.getText().toString().trim();
            Product product = new Product(byteArray, productCode, productName, productPrice, productSize);
  //          dao.insertProduct(product);
            if (edtProductCode.getText().toString().isEmpty()||edtProductName.getText().toString().isEmpty()||edtProductPrice.getText().toString().isEmpty()||edtProductSize.getText().toString().isEmpty()){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_that_bai);
                Button btn = dialog.findViewById(R.id.btnThemThatBai);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }else {
                if (dao.insertProduct(product) > 0) {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.them_thanh_cong);
                    Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                    dialog.show();
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(AddProductActivity.this, ListProductActivity.class));
                            dialog.dismiss();
                        }
                    });
                } else {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.them_that_bai);
                    Button btn = dialog.findViewById(R.id.btnThemThatBai);
                    dialog.show();
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
//            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(AddProductActivity.this,ListProductActivity.class);
//            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgProduct.setImageBitmap(bitmap);
        }
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgProduct.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 888);
        } else {
            Toast.makeText(this, "Can't open camera!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent1 = new Intent(AddProductActivity.this, MainActivity.class); // close this activity and return to preview activity (if there is any)
            startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }
}