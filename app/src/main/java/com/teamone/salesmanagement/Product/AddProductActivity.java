package com.teamone.salesmanagement.Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.ProductDAO;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity {
    private ImageView imgProduct;
    TextInputEditText tieID,tieName,tiePrice,tieAddress;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        imgProduct = findViewById(R.id.addAnhSanPham);
        tieID = findViewById(R.id.tieIdProduct);
        tieName = findViewById(R.id.tieNameProduct);
        tiePrice = findViewById(R.id.tiePriceProduct);
        tieAddress = findViewById(R.id.tieAddressProduct);

    }

    public void themAnhSanPham(View view) {
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
            Toast.makeText(this, "ko mo dc camera", Toast.LENGTH_LONG).show();
        }
    }

    public void addSanPham(View view) {
        dao = new ProductDAO(this);
        int img = imgProduct.getImageAlpha();
        String id = tieID.getText().toString();
        String name = tieName.getText().toString();
        String price = tiePrice.getText().toString();
        String address = tieAddress.getText().toString();
//        Product product = new Product(id,img,name,price,address);
    }
    public int validate(){
        int check = 1;
        if (tieID.getText().length() == 0 || tieName.getText().length() == 0
        || tiePrice.getText().length()==0 || tieAddress.getText().length()==0){
            check = -1;
        }
        return check;
    }
}