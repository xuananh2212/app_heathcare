package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.heathcare_app.model.SignupResponse;
import com.example.heathcare_app.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText textFullName, textPassword, textEmail, textConfirmPassword;
    TextView linkLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textFullName = findViewById(R.id.textFullName);
        textPassword = findViewById(R.id.textPassword);
        textEmail = findViewById(R.id.textEmail);
        textConfirmPassword = findViewById(R.id.textConfirmPassword);
        linkLogin = findViewById(R.id.linkLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = textFullName.getText().toString();
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                String confirmpassword = textConfirmPassword.getText().toString();
//                DataBase db = new DataBase(getApplicationContext(),"healthcare",null,1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirmpassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    if(isValidEmail(email)){
                        if (password.equals(confirmpassword)) {
                            if (isValid(password)) {
//                                db.register(username,email,password);
                                User user = new User(username, password, email);
                                callApiSignUp(user);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password must contain at least 8 character,having leter, digit and special character", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email is not in correct format", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    private void callApiSignUp(User user){
        Call<SignupResponse> call = ApiService.apiService.signup(user);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    SignupResponse signupResponse = response.body();
                    Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    System.out.println("Signup successful: " + signupResponse.getMessage());
                } else {
                    System.out.println("Signup failed: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                t.printStackTrace();
                System.out.println(1);
            }
        });
    }

    public static boolean isValid(String passwordheer) {
        int isLetter = 0, isDigit = 0, f3 = 0;
        if (passwordheer.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordheer.length(); p++) {
                if (Character.isLetter(passwordheer.charAt(p))) {
                    isLetter = 1;
                }
            }
            for (int r = 0; r < passwordheer.length(); r++) {
                if (Character.isDigit(passwordheer.charAt(r))) {
                    isDigit = 1;
                }
            }
            for (int s = 0; s < passwordheer.length(); s++) {
                char c = passwordheer.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (isLetter == 1 && isDigit == 1 && f3 == 1)
                return true;
            return false;
        }

    }
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
}