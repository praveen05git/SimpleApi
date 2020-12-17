package com.hencesimplified.praveenhackerearth;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;
    private WeatherAdapter weatherAdapter = new WeatherAdapter(new ArrayList<>());
    RecyclerView weatherListView;
    ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherListView = findViewById(R.id.weatherList);
        loadingView = findViewById(R.id.loadingView);

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.fetchData();

        weatherListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        weatherListView.setAdapter(weatherAdapter);

        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.weatherList.observe(this, weatherList -> {
            if (weatherList != null && weatherList instanceof List) {
                weatherListView.setVisibility(View.VISIBLE);
                weatherAdapter.updateDogsList(weatherList);
            }
        });

        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    weatherListView.setVisibility(View.GONE);
                }
            }

        });
    }
}