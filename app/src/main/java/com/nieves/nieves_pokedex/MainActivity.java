package com.nieves.nieves_pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nieves.nieves_pokedex.api.API;
import com.nieves.nieves_pokedex.fragments.PokedexFragment;
import com.nieves.nieves_pokedex.model.dto.request.LoginDto;
import com.nieves.nieves_pokedex.model.dto.response.ErrorDto;
import com.nieves.nieves_pokedex.model.dto.response.RefreshTokenDto;
import com.nieves.nieves_pokedex.model.dto.response.UserDto;
import com.nieves.nieves_pokedex.prefs.AppPreferences;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText usernameText;
    EditText passwordText;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(view -> login());

        AppPreferences.initialize(this);


    }


    private void login() {
        String username = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("debug", "Logging in with username: " + username);

        LoginDto loginDto = new LoginDto(username, password);
        Call<RefreshTokenDto> call = API.userApi().login(loginDto);

        call.enqueue(new Callback<RefreshTokenDto>() {
            @Override
            public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull Response<RefreshTokenDto> response) {
                if (response.isSuccessful()) {
                    RefreshTokenDto refreshTokenDto = response.body();
                    if (refreshTokenDto != null) {
                        AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());
                     Intent intent = new Intent(MainActivity.this, Home.class);
                     startActivity(intent);
                     finish();
                    }
                } else {
                    handleApiError(response);
                }
            }

            @Override
            public void onFailure(Call<RefreshTokenDto> call, Throwable t) {
                Log.e("debug", "Failed to login", t);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleApiError(Response<?> response) {
        ResponseBody errorBody = response.errorBody();
        try {
            if (errorBody != null) {
                String json = errorBody.string();
                ErrorDto errorDto = API.gson.fromJson(json, ErrorDto.class);
                Log.e("debug", "API Error: " + errorDto.getDetail());
                Toast.makeText(MainActivity.this, errorDto.getDetail(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (errorBody != null) {
                errorBody.close();
            }
        }
    }
}



