package com.hencesimplified.praveenhackerearth.view;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hencesimplified.praveenhackerearth.R;
import com.hencesimplified.praveenhackerearth.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private WeatherViewModel viewModel;
    private WeatherAdapter weatherAdapter = new WeatherAdapter(new ArrayList<>());
    RecyclerView weatherListView;
    ProgressBar loadingView;
    public TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherListView = findViewById(R.id.weatherList);
        loadingView = findViewById(R.id.loadingView);
        locationText = findViewById(R.id.location);

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

    @Override
    public void onLocationChanged(@NonNull Location location) {
        locationText.setText("Lat: " + location.getLatitude() + " Long: " + location.getLongitude());
    }
}