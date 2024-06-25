package com.example.heathcare_app;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.heathcare_app.api.ApiResponseBookAppointment;
import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.BookAppointment;
import com.example.heathcare_app.model.Cart;
import com.example.heathcare_app.model.CartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyMedicineBookActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productTitle, productOldPrice, productNewPrice, productDescription;
    private Button buyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_book);
        productImage = findViewById(R.id.productImage);
        productTitle = findViewById(R.id.productTitle);
        productOldPrice = findViewById(R.id.productOldPrice);
        productNewPrice = findViewById(R.id.productNewPrice);
        productDescription = findViewById(R.id.productDescription);
        buyButton = findViewById(R.id.buyButton);

        // Giả sử chúng ta nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String oldPrice = intent.getStringExtra("oldPrice");
        String newPrice = intent.getStringExtra("newPrice");
        String desc = intent.getStringExtra("desc");

        Glide.with(this).load(imageUrl).into(productImage);
        productTitle.setText(title);
        productOldPrice.setText(oldPrice);
        productNewPrice.setText(newPrice);
        productDescription.setText(desc);
        productOldPrice.setPaintFlags(productOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        buyButton.setOnClickListener(v -> showQuantityPickerDialog(id, Double.parseDouble(oldPrice), Double.parseDouble(newPrice)));
    }
    private void showQuantityPickerDialog(int productId, double oldPrice, double newPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn số lượng");

        // Inflate layout for dialog
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_quantity_picker, null);
        builder.setView(dialogView);

        final NumberPicker numberPicker = dialogView.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(1);

        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            int quantity = numberPicker.getValue();
            Cart cart = new Cart(productId,1,quantity, oldPrice, newPrice, "pending");
            callApiCreateOrder(cart);
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void callApiCreateOrder(Cart cart){
        Call<CartResponse> call  = ApiService.apiService.handleCreateOrder(cart);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    CartResponse apiResponse = response.body();
                    if(apiResponse.getStatus() == 201){
                        Toast.makeText(BuyMedicineBookActivity.this , "Đặt hàng thành công",Toast.LENGTH_SHORT ).show();
                    }
                } else {
                    Toast.makeText(BuyMedicineBookActivity.this, "Response error",Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(BuyMedicineBookActivity.this , " Failure",Toast.LENGTH_SHORT ).show();
            }
        });
    }
}