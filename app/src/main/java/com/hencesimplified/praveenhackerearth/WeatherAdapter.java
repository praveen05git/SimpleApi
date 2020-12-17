package com.hencesimplified.praveenhackerearth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ArrayList<Weather> weatherList;

    public WeatherAdapter(ArrayList<Weather> dogsList) {
        this.weatherList = weatherList;
    }

    public void updateDogsList(List<Weather> newWeatherList) {
        weatherList.clear();
        weatherList.addAll(newWeatherList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

        TextView temp = holder.itemView.findViewById(R.id.temperature);
        TextView rain = holder.itemView.findViewById(R.id.rain);
        TextView wind = holder.itemView.findViewById(R.id.wind);
        TextView date = holder.itemView.findViewById(R.id.date);

        temp.setText(weatherList.get(position).temp);
        rain.setText(weatherList.get(position).rain);
        wind.setText(weatherList.get(position).wind);
        date.setText(weatherList.get(position).time);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

}
