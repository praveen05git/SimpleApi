package com.hencesimplified.praveenhackerearth.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hencesimplified.praveenhackerearth.model.Weather;
import com.hencesimplified.praveenhackerearth.model.WeatherApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends AndroidViewModel {

    public MutableLiveData<List<Weather>> weatherList = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private WeatherApiService weatherApiService = new WeatherApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        loading.setValue(true);
        disposable.add(
                weatherApiService.getWeather()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Weather>>() {
                            @Override
                            public void onSuccess(@io.reactivex.annotations.NonNull List<Weather> weathers) {
                                try {
                                    weatherList.setValue(weathers);
                                    loading.setValue(false);
                                    Toast.makeText(getApplication(), "Retrieved from endpoint", Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }
}
