package com.example.heathcare_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Tạo dữ liệu mẫu
        productList = new ArrayList<>();
        productList.add(new Product(1,"Product 1", 123, 111, "https://picsum.photos/200"));
        productList.add(new Product(2,"Product 2", 150, 120, "https://picsum.photos/200"));
        productList.add(new Product(3,"Product 3", 123, 111, "https://picsum.photos/200"));
        productList.add(new Product(4,"Product 4", 150, 120, "https://picsum.photos/200"));
        productList.add(new Product(5,"Product 5", 123, 111, "https://picsum.photos/200"));
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }
}
