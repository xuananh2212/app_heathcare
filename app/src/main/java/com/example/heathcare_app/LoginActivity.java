package com.example.heathcare_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.LoginResponse;
import com.example.heathcare_app.model.SignupResponse;
import com.example.heathcare_app.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText textEmail, textPassword;
    TextView linkRegister;
    Button btnLogin;
//    Acount acount1 = new Acount("datnv","123");
//    Acount acount2 = new Acount("xuanhanhnx","123");
//    Acount acount3 = new Acount("thend","123");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // start tao tk de dangnhap thu cong
//        Acount acount1 = new Acount("datnv","123");
//        Acount acount2 = new Acount("tuananhnx","123");
//        Acount acount3 = new Acount("thend","123");
//        ArrayList<Acount> acounts = new ArrayList<>();
//        acounts.add((acount1));
//        acounts.add((acount2));
//        acounts.add((acount3));
        // end tao tai khoan de danhnhap thu cong
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        linkRegister = findViewById(R.id.linkRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
//                DataBase db = new DataBase(getApplicationContext(),"healthcare",null,1);
                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                } else {
                    // start dangnhap voi tk cho san
//                    boolean isAuthenticated = false;
//                    for (Acount account : acounts) {
//                        if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
//                            isAuthenticated = true;
//                            break;
//                        }
//                    }
//                    if (isAuthenticated) {
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
//                    }
                    // end dangnhap voi tk cho san
                    // start dangnhap voi db
//                    if(db.login(username,password) == 1){
//                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username",username);
//                        editor.apply();
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
//                    }
                }

                //end dangnhap voi db
//                if (username.equals(acount1.getUsername()) && password.equals(acount1.getPassword())){
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
//                }
                User user = new User(password, email);
                callApiLogin(user);

            }
        });
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void callApiLogin(User user) {
        Call<LoginResponse> call = ApiService.apiService.login(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    Log.e("responseapi", "onResponse: " + signupResponse);
//                    Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_SHORT).show();
                    Log.d("responseapi", loginResponse.getMessage());
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                    System.out.println("Signup successful: " + signupResponse.getMessage());
                } else {
                    Toast.makeText(getApplicationContext(),"Signup failed",Toast.LENGTH_SHORT).show();
//                    LoginResponse loginResponse = response.body();
//                    Log.d("responseapi", loginResponse.getMessage());

//                    ErrorSignupResponse errorSignupResponse = response.body();
//                    Log.d("responseapi", errorSignupResponse.getMessage());

//                    Toast.makeText(getApplicationContext(),"Register failed",Toast.LENGTH_SHORT).show();
//                    System.out.println("Signup failed: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
//                System.out.println("Call Api lỗi");
                Toast.makeText(getApplicationContext(), "Call Api Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}