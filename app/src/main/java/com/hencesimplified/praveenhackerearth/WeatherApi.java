package com.hencesimplified.praveenhackerearth;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("5d3a99ed2f0000bac16ec13a")
    Single<List<Weather>> getWeather();
}
