package com.hencesimplified.praveenhackerearth.model;

import com.hencesimplified.praveenhackerearth.model.Weather;
import com.hencesimplified.praveenhackerearth.model.WeatherApi;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiService {
    private static final String BASE_URL = "https://raw.githubusercontent.com";
    //private static final String BASE_URL = "https://www.mocky.io";
    private WeatherApi api;

    public WeatherApiService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
    }

    public Single<List<Weather>> getWeather() {
        return api.getWeather();
    }
}
