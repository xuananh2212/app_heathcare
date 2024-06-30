package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.heathcare_app.model.ApiResponseDoctor;
import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.Doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorDetailsActivity extends AppCompatActivity {
    TextView tv;
    Button btn;
    List<HashMap<String, String>> doctor_details = new ArrayList<>();
    ArrayList<HashMap<String, String>> list;
    List<Doctor> doctors;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        String id = it.getStringExtra("id");
        tv.setText(title);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        callApiGetDoctors(id);
    }

    private void callApiGetDoctors(String id) {
        ApiService.apiService.getListDoctors(Integer.valueOf(id)).enqueue(new Callback<ApiResponseDoctor>() {
            @Override
            public void onResponse(Call<ApiResponseDoctor> call, Response<ApiResponseDoctor> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseDoctor apiResponse = response.body();
                    if (apiResponse.getStatus() == 200) {
                        doctors = apiResponse.getData().getDoctors();
                        for (Doctor doctor : doctors) {
                            HashMap<String, String> details = new HashMap<>();
                            details.put("name", "Tên bác sĩ: " + doctor.getName());
                            details.put("address", "Điạ chỉ: " + doctor.getAddress());
                            details.put("experience", "Kinh nghiệm: " + doctor.getExp() + " năm");
                            details.put("phone", "SĐT: " + doctor.getPhone());
                            details.put("price", String.valueOf(doctor.getPrice()) +"VND");
                            details.put("image", doctor.getImage());
                            details.put("id", String.valueOf(doctor.getId()));
                            doctor_details.add(details);
                        }
                        populateListView();
                    } else {
                        Toast.makeText(DoctorDetailsActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoctorDetailsActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseDoctor> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DoctorDetailsActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView() {
        list = new ArrayList<>(doctor_details);
        sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[]{"name", "address", "experience", "phone", "price", "image"}, new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.productImage}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView imageView = view.findViewById(R.id.productImage);
                String imageUrl = ((HashMap<String, String>) getItem(position)).get("image");
                Glide.with(DoctorDetailsActivity.this).load(imageUrl).into(imageView);
                return view;
            }
        };
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", tv.getText().toString());
                it.putExtra("text2", doctor_details.get(i).get("name"));
                it.putExtra("text3", doctor_details.get(i).get("address"));
                it.putExtra("text4", doctor_details.get(i).get("experience"));
                it.putExtra("text5", doctor_details.get(i).get("phone"));
                it.putExtra("text6", doctor_details.get(i).get("price"));
                it.putExtra("text7", doctor_details.get(i).get("id"));
                startActivity(it);
            }
        });
    }
}
