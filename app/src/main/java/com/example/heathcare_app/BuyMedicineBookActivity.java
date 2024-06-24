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
        buyButton.setOnClickListener(v -> showQuantityPickerDialog());
    }
    private void showQuantityPickerDialog() {
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
            // Xử lý logic sau khi chọn số lượng
            // Ví dụ: thêm sản phẩm vào giỏ hàng với số lượng đã chọn
            // hoặc mở trang thanh toán.
            // showToast("Số lượng đã chọn: " + quantity);
            handlePurchase(quantity);
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void handlePurchase(int quantity) {
        // Logic xử lý khi nhấn nút "Xác nhận"
        // Ví dụ: Hiển thị một toast thông báo số lượng đã chọn
        // hoặc thêm sản phẩm vào giỏ hàng với số lượng đã chọn
        // Đây chỉ là ví dụ đơn giản, bạn có thể thay đổi logic theo nhu cầu
        Toast.makeText(this, "Số lượng đã chọn: " + quantity, Toast.LENGTH_SHORT).show();
    }
}