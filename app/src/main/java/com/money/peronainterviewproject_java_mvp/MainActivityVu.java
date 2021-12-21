package com.money.peronainterviewproject_java_mvp;

import com.money.peronainterviewproject_java_mvp.json.WeatherTime;

import java.util.ArrayList;

public interface MainActivityVu {
    String getDataErrorString();

    void showToast(String message);

    void showProgressBar(boolean isShow);

    void showWeatherInformation(ArrayList<WeatherTime> timeArrayList);
}
