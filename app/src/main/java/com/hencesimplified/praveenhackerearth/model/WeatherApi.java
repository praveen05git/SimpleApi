package com.hencesimplified.praveenhackerearth.model;

import com.hencesimplified.praveenhackerearth.model.Weather;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WeatherApi {
//@GET("v2/5d3a99ed2f0000bac16ec13a")

    @GET("praveen05git/weatherdata/master/weatherApi.json")
    Single<List<Weather>> getWeather();
}
