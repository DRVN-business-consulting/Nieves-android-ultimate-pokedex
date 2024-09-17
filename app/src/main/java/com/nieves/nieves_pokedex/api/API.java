package com.nieves.nieves_pokedex.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nieves.nieves_pokedex.interceptor.AuthInterceptor;
import com.google.gson.Strictness;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class API {
    private static final String BASE_URL = "https://a508-202-90-134-36.ngrok-free.app/";

    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setStrictness(Strictness.LENIENT)
            .create();
//    private static String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqaXJlaCIsImV4cCI6MTcyNzA2MzQwMn0.3jeFEjXtYXXAc-wzc5YX3TuhFKz_OFM0C948-Tey8bE";

    private static final HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final AuthInterceptor AUTH_INTERCEPTOR = new AuthInterceptor();

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .addInterceptor(HTTP_LOGGING_INTERCEPTOR)
            .addInterceptor(AUTH_INTERCEPTOR)
            .build();

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OK_HTTP_CLIENT)
            .build();

    public static UserApi userApi() {
        return RETROFIT.create(UserApi.class);
    }
}
