package com.teamone.salesmanagement.Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.teamone.salesmanagement.Customer.Customer;
import com.teamone.salesmanagement.Customer.EditCustomerActivity;
import com.teamone.salesmanagement.Customer.ListCustomerActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;
import com.teamone.salesmanagement.database.ProductDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgProduct;
    EditText edtProductName, edtProductPrice, edtProductSize;
    ProductDAO dao;
    byte[] productImage;
    String productCode, productName, productSize;
    double productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgProduct = findViewById(R.id.productPicture);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductSize = findViewById(R.id.edtProductSize);

        Bundle bundle = getIntent().getExtras();
        productImage = bundle.getByteArray("image");
        productCode = bundle.getString("code");
        productName = bundle.getString("name");
        productPrice = bundle.getDouble("price");
        productSize = bundle.getString("size");

        imgProduct.setImageBitmap(getImage(productImage));
        edtProductName.setText(productName);
        edtProductPrice.setText(String.valueOf(productPrice));
        edtProductSize.setText(productSize);
    }


    public void editProduct(View view) {
        dao = new ProductDAO(this);
        byte[] productImage = convertImageToByteArray();
        String productName = edtProductName.getText().toString().trim();
        double productPrice = Double.parseDouble(edtProductPrice.getText().toString().trim());
        String productSize = edtProductSize.getText().toString().trim();
        Product product = new Product(productImage, productCode, productName, productPrice, productSize);
        if (edtProductName.getText().length()==0 || edtProductPrice.getText().length()==0
                ||  edtProductSize.getText().length()==0){
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
            if (dao.updateProduct(product) > 0){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_thanh_cong);
                Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(EditProductActivity.this, ListProductActivity.class));
                        dialog.dismiss();
                    }
                });
            }else {
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
    }

    public void addProductPicture1(View view) {
        Dialog dialog = new Dialog(EditProductActivity.this);
        dialog.setContentView(R.layout.themanh);
        Button button1 = dialog.findViewById(R.id.btnCamera);
        Button button2 = dialog.findViewById(R.id.btnStorage);
        dialog.show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(EditProductActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 888);
                } else {
                    ActivityCompat.requestPermissions(EditProductActivity.this, new String[]{Manifest.permission.CAMERA}, 999);
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

    public byte[] convertImageToByteArray() {
        // Get image from ImageView
        imgProduct.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) imgProduct.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
      // bitmap.recycle();
        return byteArray;
    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.delete:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.xoa);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                Button btnDongY = dialog.findViewById(R.id.btnDongY);
                dialog.show();
                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao = new ProductDAO(EditProductActivity.this);
                        if (dao.delProduct(productCode) <= 0){
                            startActivity(new Intent(EditProductActivity.this, ListProductActivity.class));
                        }else {
                            dialog.setContentView(R.layout.xoa_thanh_cong);
                            Button btnOK = dialog.findViewById(R.id.ok);
                            dialog.show();
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(EditProductActivity.this, ListProductActivity.class));
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
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
}