package com.example.heathcare_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.ApiResponse;
import com.example.heathcare_app.model.BodyUpdate;
import com.example.heathcare_app.model.BodyUpdateCart;
import com.example.heathcare_app.model.BodyUpdateStatusCart;
import com.example.heathcare_app.model.Carts;
import com.example.heathcare_app.model.CartsAdapter;
import com.example.heathcare_app.model.CartsAdapterPayed;
import com.example.heathcare_app.model.DataCarts;
import com.example.heathcare_app.model.DataCarts.*;
import com.example.heathcare_app.model.ItemCarts;
import com.example.heathcare_app.model.SharedPrefManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {
    Button btnBack;
    private TextView tvTotal;
    private List<ItemCarts> itemCartsList;
    private List<Integer> listIdItem;
    private CartsAdapter cartsAdapter;
    private CartsAdapterPayed cartsAdapterPayed;
    //
    private Spinner spinnerStatus;
    //

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        spinnerStatus = findViewById(R.id.spinnerStatus);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStatus = parent.getItemAtPosition(position).toString();
                // Handle selected status (pending or done)
                if (selectedStatus.equals("Chờ thanh toán")) {
                    // Handle pending status
                    // Call API or perform actions related to pending status
                    String strId = SharedPrefManager.getInstance(OrderDetailsActivity.this).getString("id", "null");
                    String status = "pending";
                    callApiGetOrderDetails(strId, status);
                    tvTotal = findViewById(R.id.tvTotal);
                    Button btnCheckout = findViewById(R.id.btnCheckout);
                    btnCheckout.setEnabled(true);
                    btnCheckout.setVisibility(View.VISIBLE);
                    listIdItem = new ArrayList<>();
                    itemCartsList = new ArrayList<>();
                    btnCheckout.setOnClickListener(v -> {
                        Toast.makeText(OrderDetailsActivity.this, "Đã thanh toán " + tvTotal.getText(), Toast.LENGTH_SHORT).show();
                        // Xử lý sự kiện khi nhấn nút Thanh toán
                        String strListItem = listIdItem.toString();
                        for (Integer id1 : listIdItem) {
                            BodyUpdateStatusCart bodyUpdateStatusCart = new BodyUpdateStatusCart("confirm");
                            BodyUpdate<BodyUpdateStatusCart> bodyUpdate = new BodyUpdate(id1, bodyUpdateStatusCart);
                            Log.d("responseapi", bodyUpdate.toString());
                            callApiUpdateItemOrderDetails(bodyUpdate);
                        }
                        itemCartsList.clear();
                        cartsAdapter.notifyDataSetChanged(); // Notify adapter of changes
                        updateTotal();
                    });
                } else if (selectedStatus.equals("Chờ xác nhận")) {
                    // Handle done status
                    // Call API or perform actions related to done status
                    String strId = SharedPrefManager.getInstance(OrderDetailsActivity.this).getString("id", "null");
                    String status = "confirm";
                    callApiGetOrderDetailsPayed(strId, status);
                    tvTotal = findViewById(R.id.tvTotal);
                    Button btnCheckout = findViewById(R.id.btnCheckout);
                    btnCheckout.setEnabled(false);
                    btnCheckout.setVisibility(View.INVISIBLE);
                    listIdItem = new ArrayList<>();
                    itemCartsList = new ArrayList<>();
                }
                else if(selectedStatus.equals("Đã thanh toán")){
                    String strId = SharedPrefManager.getInstance(OrderDetailsActivity.this).getString("id", "null");
                    String status = "done";
                    callApiGetOrderDetailsPayed(strId, status);
                    tvTotal = findViewById(R.id.tvTotal);
                    Button btnCheckout = findViewById(R.id.btnCheckout);
                    btnCheckout.setEnabled(false);
                    btnCheckout.setVisibility(View.INVISIBLE);
                    listIdItem = new ArrayList<>();
                    itemCartsList = new ArrayList<>();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no item selected
            }
        });
//
//        String strId = SharedPrefManager.getInstance(OrderDetailsActivity.this).getString("id", "null");
//        String status = "pending";
//        callApiGetOrderDetails(strId, status);
//        tvTotal = findViewById(R.id.tvTotal);
//        Button btnCheckout = findViewById(R.id.btnCheckout);
//        listIdItem = new ArrayList<>();
//        itemCartsList = new ArrayList<>();
////        cartsAdapter = new CartsAdapter(itemCartsList, this::updateTotal);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setAdapter(cartsAdapter);
//        btnCheckout.setOnClickListener(v -> {
////            Log.d("responseapi", strListItem);
//            Toast.makeText(OrderDetailsActivity.this, "Đã thanh toán " + tvTotal.getText(), Toast.LENGTH_SHORT).show();
//            // Xử lý sự kiện khi nhấn nút Thanh toán
//            String strListItem = listIdItem.toString();
//            for (Integer id : listIdItem) {
//                BodyUpdateStatusCart bodyUpdateStatusCart = new BodyUpdateStatusCart("done");
//                BodyUpdate<BodyUpdateStatusCart> bodyUpdate = new BodyUpdate(id, bodyUpdateStatusCart);
//                Log.d("responseapi", bodyUpdate.toString());
//                callApiUpdateItemOrderDetails(bodyUpdate);
//            }
//            itemCartsList.clear();
//            cartsAdapter.notifyDataSetChanged(); // Notify adapter of changes
//            updateTotal();
//        });
//        updateTotal();
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });
    }

    private void callApiGetOrderDetails(String id, String status) {
        Call<ApiResponse<DataCarts>> call = ApiService.apiService.handleGetOrderDetails(Integer.valueOf(id), status);
        call.enqueue(new Callback<ApiResponse<DataCarts>>() {
            @Override
            public void onResponse(Call<ApiResponse<DataCarts>> call, Response<ApiResponse<DataCarts>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<DataCarts> responseOrderDetails = response.body();
                    Log.d("responseapi", responseOrderDetails.getMessage());
                    for (Carts carts : responseOrderDetails.getData().getCarts()) {

                        int id = carts.getId();
                        listIdItem.add(id);
                        String name = carts.getName();
                        String img = carts.getImage();
                        Log.d("responseapi",img);
                        float price = (float) carts.getNew_price();
                        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

                        // Định dạng giá trị price sang VND
                        String formattedPrice = vndFormat.format(price);
                        int quantity = carts.getQuantity();
                        ItemCarts itemCart = new ItemCarts(name, price, quantity, id,img);
                        Log.d("responseapi",itemCart.toString());
                        itemCartsList.add(itemCart);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));
                    cartsAdapter = new CartsAdapter(itemCartsList, OrderDetailsActivity.this::updateTotal, listIdItem);
//                    cartsAdapter = new CartsAdapter(itemCartsList, OrderDetailsActivity.this);
                    recyclerView.setAdapter(cartsAdapter);
                    updateTotal();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DataCarts>> call, Throwable t) {

            }
        });
    }

    private void callApiGetOrderDetailsPayed(String id, String status) {
        Call<ApiResponse<DataCarts>> call = ApiService.apiService.handleGetOrderDetails(Integer.valueOf(id), status);
        call.enqueue(new Callback<ApiResponse<DataCarts>>() {
            @Override
            public void onResponse(Call<ApiResponse<DataCarts>> call, Response<ApiResponse<DataCarts>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<DataCarts> responseOrderDetails = response.body();
                    Log.d("responseapi", responseOrderDetails.getMessage());
                    for (Carts carts : responseOrderDetails.getData().getCarts()) {

                        int id = carts.getId();
                        listIdItem.add(id);
                        String name = carts.getName();
                        String img = carts.getImage();
                        Log.d("responseapi",img);
                        float price = (float) carts.getNew_price();
                        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

                        // Định dạng giá trị price sang VND
                        String formattedPrice = vndFormat.format(price);
                        int quantity = carts.getQuantity();
                        ItemCarts itemCart = new ItemCarts(name, price, quantity, id,img);
                        Log.d("responseapi",itemCart.toString());
                        itemCartsList.add(itemCart);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));
                    cartsAdapterPayed = new CartsAdapterPayed(itemCartsList, OrderDetailsActivity.this::updateTotal, listIdItem);
//                    cartsAdapter = new CartsAdapter(itemCartsList, OrderDetailsActivity.this);
                    recyclerView.setAdapter(cartsAdapterPayed);
                    updateTotal();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DataCarts>> call, Throwable t) {

            }
        });
    }

    public void callApiUpdateItemOrderDetails(BodyUpdate bodyUpdate) {
        Call<ApiResponse<Object>> call = ApiService.apiService.handleUpdateItemOrderDetails(bodyUpdate);
        call.enqueue(new Callback<ApiResponse<Object>>() {
            @Override
            public void onResponse(Call<ApiResponse<Object>> call, Response<ApiResponse<Object>> response) {
                Log.d("responseapi", "Vao goi api success");
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("responseapi", response.body().toString());
                } else {
                    Log.d("responseapi", "Cap nhat loi");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Object>> call, Throwable t) {
                Log.d("responseapi", "Vao goi api fail");
                t.printStackTrace();
                Log.d("responseapi", "Call Api lỗi");
                //Toast.makeText(OrderDetailsActivity.this, "Call Api Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotal() {
        int total = 0;
        for (ItemCarts itemCarts : itemCartsList) {
            total += itemCarts.getPrice() * itemCarts.getQuantity();
        }
        // Tạo một instance của NumberFormat cho định dạng tiền tệ Việt Nam Đồng
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        // Định dạng giá trị price sang VND
        String formattedPrice = vndFormat.format(total);
        tvTotal.setText("Tổng: " + formattedPrice);
    }

}