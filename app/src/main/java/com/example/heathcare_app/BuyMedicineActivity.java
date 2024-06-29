package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.Medicine;
import com.example.heathcare_app.model.MedicineResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyMedicineActivity extends AppCompatActivity {
ImageButton btnCart;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Medicine> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        fetchMedicines();
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(BuyMedicineActivity.this, OrderDetailsActivity.class));
                Intent intent = new Intent(BuyMedicineActivity.this, OrderDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    private void fetchMedicines() {
        Call<MedicineResponse> call = ApiService.apiService.getMedicines();
        call.enqueue(new Callback<MedicineResponse>() {
            @Override
            public void onResponse(Call<MedicineResponse> call, Response<MedicineResponse> response) {
                if (response.isSuccessful()) { // Đây là phương thức kiểm tra response code
                    MedicineResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null) {
                        List<Medicine> medicines = apiResponse.getData().getMedicines();
                        productList = new ArrayList<>();
                        productList.addAll(medicines);
                        productAdapter = new ProductAdapter(BuyMedicineActivity.this, medicines);
                        recyclerView.setAdapter(productAdapter);
                    }
                } else {
                    // Xử lý lỗi từ server
                    Log.e("MainActivity", "Server Error");
                }
            }
            @Override
            public void onFailure(Call<MedicineResponse> call, Throwable t) {
                t.printStackTrace();
                // Handle network error
            }
        });
    }
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent); // Optional: Ensure app exits if this is the root activity
//    }
}
