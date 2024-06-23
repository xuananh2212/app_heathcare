package com.example.heathcare_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.heathcare_app.api.ApiResponseBookAppointment;
import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.BookAppointment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog timePickerDialogEnd;
    private Button dateButton, timeButton,timeButtonEnd ,btnBook, btnBack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        timeButtonEnd = findViewById(R.id.buttonAppTimeEnd);
        btnBook =findViewById(R.id.buttonBookAppointment);
        btnBack= findViewById(R.id.buttonAppBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");
        String id = it.getStringExtra("text7");
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:" + fees + "/-");

        // datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // timePicker
        initTimePicker();
        initTimePickerEnd();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        timeButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogEnd.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = dateButton.getText().toString();
                String selectedTime = timeButton.getText().toString();
                String selectedTimeEnd = timeButtonEnd.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String startTimeStr = selectedDate + " " + selectedTime;
                String endTimeStr = selectedDate + " " + selectedTimeEnd;
                try {
                    Date startTime  = dateFormat.parse(startTimeStr);
                    Date endTime = dateFormat.parse(endTimeStr);
                    BookAppointment bookAppointment = new BookAppointment(7, Integer.parseInt(id), new Date(), new Date());
                    callApiAddBookAppointments(bookAppointment);

                }catch (ParseException e){
                    e.printStackTrace();
                    Toast.makeText(BookAppointmentActivity.this, "Invalid date or time format", Toast.LENGTH_SHORT).show();
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }
//                catch (java.text.ParseException e) {
//
//                    throw new RuntimeException(e);
//                }


            }
        });



    }
    private void callApiAddBookAppointments(BookAppointment bookAppointment){
        Call<ApiResponseBookAppointment> call  = ApiService.apiService.handleBookAppointments(bookAppointment);
        call.enqueue(new Callback<ApiResponseBookAppointment>() {
            @Override
            public void onResponse(Call<ApiResponseBookAppointment> call, Response<ApiResponseBookAppointment> response) {
                if (response.isSuccessful()) {
                    ApiResponseBookAppointment apiResponse = response.body();
                    if(apiResponse.getStatus() == 201){
                        Toast.makeText(BookAppointmentActivity.this , "Đặt lịch thành công",Toast.LENGTH_SHORT ).show();
                    }
                } else {
                   Toast.makeText(BookAppointmentActivity.this , "Response error",Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseBookAppointment> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(BookAppointmentActivity.this , " Failure",Toast.LENGTH_SHORT ).show();
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker , int i , int i1, int i2){
                i1 = i1 + 1;
                   dateButton.setText(i2 + "/" + i1 + "/" + i);
            }

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog  = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+ 86400000);


    };
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 timeButton.setText(hourOfDay +":" +minute);
            }

        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins  = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins , true);



    };
    private void initTimePickerEnd(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButtonEnd.setText(hourOfDay + ":" + minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins  = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialogEnd = new TimePickerDialog(this, style, timeSetListener, hrs, mins , true);



    };
}

