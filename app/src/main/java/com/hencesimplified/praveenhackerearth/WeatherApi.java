package com.hencesimplified.praveenhackerearth;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("praveen05git/weatherdata/master/weatherApi.json")
    Single<List<Weather>> getWeather();
}
