package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
Button btnBack;
ListView listViewOrderDetail;
    private ArrayList<HashMap<String, String>> detailList;
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
        btnBack = findViewById(R.id.btnBack);
        listViewOrderDetail = findViewById(R.id.listViewOrderDetails);
        detailList = new ArrayList<>();
        HashMap<String, String> contact1 = new HashMap<>();
        contact1.put("name", "Nguyen Van Dat");
        contact1.put("phone", "05869452391");
        contact1.put("birthDate", "02/02/2002");
        contact1.put("address", "123 Đường Láng, Đống Đa, Hà Nội");
        detailList.add(contact1);
        HashMap<String, String> contact2 = new HashMap<>();
        contact2.put("name", "Nguyen Van Binh");
        contact2.put("phone", "0237865483");
        contact2.put("birthDate", "02/10/2001");
        contact2.put("address", "654 Trường Chinh, Thanh Xuân, Hà Nội");
        detailList.add(contact2);
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                detailList,
                android.R.layout.simple_list_item_2,
//                android.R.layout.list_item_contact,
                new String[]{"name", "phone"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listViewOrderDetail.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });
    }
}