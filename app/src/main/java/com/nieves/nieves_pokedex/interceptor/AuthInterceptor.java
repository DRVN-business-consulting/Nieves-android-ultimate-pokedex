package com.nieves.nieves_pokedex.interceptor;

import androidx.annotation.NonNull;

import com.nieves.nieves_pokedex.api.API;
import com.nieves.nieves_pokedex.model.dto.response.RefreshTokenDto;
import com.nieves.nieves_pokedex.prefs.AppPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String url = originalRequest.url().toString();
        String accessToken = AppPreferences.getInstance().getAccessToken();

        if (!url.endsWith("/refresh-token") && accessToken != null) refreshToken();

        Request newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();

        return chain.proceed(newRequest);
    }

    private void refreshToken() {
        API.userApi().refreshToken().enqueue(new Callback<RefreshTokenDto>() {
            @Override
            public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull retrofit2.Response<RefreshTokenDto> response) {
                if (response.isSuccessful()) {
                    RefreshTokenDto refreshTokenDto = response.body();
                    if (refreshTokenDto != null) {
                        AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RefreshTokenDto> call, @NonNull Throwable t) {
                AppPreferences.getInstance().setAccessToken(null);
            }
        });
    }
}
