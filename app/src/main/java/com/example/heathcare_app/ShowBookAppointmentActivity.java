package com.example.heathcare_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.ApiResponseDoctor;
import com.example.heathcare_app.model.ApiResponseGetBookAppointment;
import com.example.heathcare_app.model.Appointment;
import com.example.heathcare_app.model.Doctor;
import com.example.heathcare_app.model.SharedPrefManager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowBookAppointmentActivity extends AppCompatActivity {
    Button btn;
    List<HashMap<String, String>> bookAppointmentDetails = new ArrayList<>();
    ArrayList<HashMap<String, String>> list;
    List<Appointment> bookAppointment;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_book_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn = findViewById(R.id.buttonDDBackBA);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowBookAppointmentActivity.this, HomeActivity.class));
            }
        });
        String strId =   SharedPrefManager.getInstance(ShowBookAppointmentActivity.this).getString("id","null");
        callApiGetBookAppoint(strId);
    }
    private void callApiGetBookAppoint(String id) {
        ApiService.apiService.handleGetBookAppointments(Integer.valueOf(id)).enqueue(new Callback<ApiResponseGetBookAppointment>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ApiResponseGetBookAppointment> call, Response<ApiResponseGetBookAppointment> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGetBookAppointment apiResponse = response.body();
                    if (apiResponse.getStatus() == 200) {
                        bookAppointment = apiResponse.getData().getAppointments();
                        System.out.println("bookAppointment" + bookAppointment);
                        for (Appointment appointment : bookAppointment) {
                            HashMap<String, String> details = new HashMap<>();
                            details.put("name", "Tên bác sĩ: " + appointment.getDoctor().getName());
                            details.put("address", "Địa chỉ: " + appointment.getDoctor().getAddress());
                            details.put("experience", "Kinh nghiệm: " + appointment.getDoctor().getExp() + " years");
                            details.put("phone", "SĐT: " + appointment.getDoctor().getPhone());
                            details.put("price", "Giá (1h): " + String.valueOf(appointment.getDoctor().getPrice()));
                            details.put("image", appointment.getDoctor().getImage());
                            Instant instant = Instant.parse(appointment.getStartTime());
                            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Bangkok"));
                            Instant instantEnd = Instant.parse(appointment.getEndTime());
                            LocalDateTime dateTimeEnd = LocalDateTime.ofInstant(instantEnd, ZoneId.of("Asia/Bangkok"));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            String formattedDateTime = dateTime.format(formatter);
                            String formattedDateTimeEnd = dateTimeEnd.format(formatter);
                            details.put("startTime","Thời gian từ: " + formattedDateTime );
                            details.put("endTime", "Thời gian đến" + formattedDateTimeEnd);
                            details.put("id", String.valueOf(appointment.getDoctor().getId()));
                            bookAppointmentDetails.add(details);
                        }
                        populateListView();
                    } else {
                        Toast.makeText(ShowBookAppointmentActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShowBookAppointmentActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGetBookAppointment> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ShowBookAppointmentActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView() {
        list = new ArrayList<>(bookAppointmentDetails);
        sa = new SimpleAdapter(this, list, R.layout.multi_lines_list, new String[]{"name", "address", "experience", "phone", "price","startTime","endTime" , "image"}, new int[]{R.id.line_aBA, R.id.line_bBA, R.id.line_cBA, R.id.line_dBA, R.id.line_eBA,R.id.line_fBA,R.id.line_gBA, R.id.productImageBA}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView imageView = view.findViewById(R.id.productImageBA);
                String imageUrl = ((HashMap<String, String>) getItem(position)).get("image");
                Glide.with(ShowBookAppointmentActivity.this).load(imageUrl).into(imageView);
                return view;
            }
        };
        ListView lst = findViewById(R.id.listViewDDBA);
        lst.setAdapter(sa);
//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Intent it = new Intent(ShowBookAppointmentActivity.this, BookAppointmentActivity.class);
//                it.putExtra("text1", tv.getText().toString());
//                it.putExtra("text2", doctor_details.get(i).get("name"));
//                it.putExtra("text3", doctor_details.get(i).get("address"));
//                it.putExtra("text4", doctor_details.get(i).get("experience"));
//                it.putExtra("text5", doctor_details.get(i).get("phone"));
//                it.putExtra("text6", doctor_details.get(i).get("price"));
//                it.putExtra("text7", doctor_details.get(i).get("id"));
//                startActivity(it);
//            }
//        });
    }
}