package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView familyPhysicianImageView = findViewById(R.id.familyPhysicianImageView);
        ImageView dieticianImageView = findViewById(R.id.dieticianImageView);
        ImageView dentistImageView = findViewById(R.id.dentistImageView);
        ImageView surgeonImageView = findViewById(R.id.surgeonImageView);
        ImageView cardiologistsImageView = findViewById(R.id.cardiologistsImageView);


        Glide.with(this).load(R.drawable.family_physicians).transform(new CircleCrop()).into(familyPhysicianImageView);
        Glide.with(this).load(R.drawable.dietician).transform(new CircleCrop()).into(dieticianImageView);
        Glide.with(this).load(R.drawable.dentist).transform(new CircleCrop()).into(dentistImageView);
        Glide.with(this).load(R.drawable.surgeon).transform(new CircleCrop()).into(surgeonImageView);
        Glide.with(this).load(R.drawable.cardiologists).transform(new CircleCrop()).into(cardiologistsImageView);

        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView familyphysician = findViewById(R.id.cardFDFamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Nhóm bác sĩ gia đình");
                it.putExtra("id", "1");
                startActivity(it);
            }
        });

        CardView dietician = findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Chuyên gia dinh dưỡng");
                it.putExtra("id", "2");
                startActivity(it);
            }
        });
        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Nha sĩ");
                it.putExtra("id", "3");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Bác sĩ phẫu thuật");
                it.putExtra("id", "4");
                startActivity(it);
            }
        });

        CardView cardiologists = findViewById(R.id.cardFDCardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Bác sĩ tim mạch");
                it.putExtra("id", "5");
                startActivity(it);
            }
        });


    }
}