package com.money.peronainterviewproject_java_mvp.api;

import com.money.peronainterviewproject_java_mvp.json.WeatherObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestApi {


    @GET("F-C0032-001?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&locationName=臺北市&elementName=MinT")
    Observable<WeatherObject> getWeatherApi();


}
