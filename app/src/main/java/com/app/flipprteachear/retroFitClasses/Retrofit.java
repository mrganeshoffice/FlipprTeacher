package com.app.flipprteachear.retroFitClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    //static String baseurl = "https://flippr.in/api_sandbox/";
    static String baseurl = "https://flippr.in/api/";// "https://flippr.in/api/"

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okhttpClient = new OkHttpClient.Builder().addInterceptor(logging)
                    .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES) //
                   .build();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okhttpClient)
                    .build();
        }

        return retrofit;
    }
}
