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
import com.example.heathcare_app.model.ApiResponse;
import com.example.heathcare_app.model.LoginResponse;
import com.example.heathcare_app.model.Metadata;
import com.example.heathcare_app.model.SharedPrefManager;
import com.example.heathcare_app.model.SignupResponse;
import com.example.heathcare_app.model.User;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText textEmail, textPassword;
    TextView linkRegister;
    Button btnLogin;

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
        textEmail = findViewById(R.id.textEmail);
        String emailSaved = SharedPrefManager.getInstance(LoginActivity.this).getString("email", "");
        Log.d("responseapi", "Default Email" + emailSaved);
        textEmail.setText(emailSaved);
        textPassword = findViewById(R.id.textPassword);
        linkRegister = findViewById(R.id.linkRegister);
        btnLogin = findViewById(R.id.btnLogin);
        String savedUserId = SharedPrefManager.getInstance(LoginActivity.this).getString("id", "");
        if (!savedUserId.isEmpty()) {
            // User ID found, navigate to HomeActivity
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish(); // Finish LoginActivity to prevent returning to it on back press
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đủ các trường", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(password, email);
                    callApiLogin(user);
                }

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
        Call<ApiResponse<Metadata>> call = ApiService.apiService.login(user);
        call.enqueue(new Callback<ApiResponse<Metadata>>() {
            @Override
            public void onResponse(Call<ApiResponse<Metadata>> call, Response<ApiResponse<Metadata>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<Metadata> loginResponse = response.body();
//                    Log.d("responseapi", loginResponse.getMessage());
                    Metadata metadata = loginResponse.getData();
//                    Log.d("responseapi", metadata.toString());
                    int id = metadata.getId();
                    String email = metadata.getEmail();
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(LoginActivity.this).saveString("id", Integer.toString(id));
                    SharedPrefManager.getInstance(LoginActivity.this).saveString("email", email);
                    String emailAfterLogin = SharedPrefManager.getInstance(LoginActivity.this).getString("email", "");
                    Log.d("responseapi", "Email after login" + emailAfterLogin);
                    String strId = SharedPrefManager.getInstance(LoginActivity.this).getString("id", "null");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    Log.d("responseapi", strId);
                } else {
                    Log.d("responseapi", "Login fail");
                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            ApiResponse<Metadata> errorResponse = new Gson().fromJson(errorBody, ApiResponse.class);
                            Log.d("responseapi", "Parsed error response: " + errorResponse.toString());
                            if(errorResponse.getStatus() == 401){
                                Toast.makeText(getApplicationContext(), "Sai mật khẩu vui lòng thử lại", Toast.LENGTH_SHORT).show();
                            } else if(errorResponse.getStatus() == 403) {
                                Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("responseapi", "ErrorBody is null");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Metadata>> call, Throwable t) {
                t.printStackTrace();
                Log.d("responseapi", "Call Api lỗi");
                Toast.makeText(getApplicationContext(), "Call Api Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}