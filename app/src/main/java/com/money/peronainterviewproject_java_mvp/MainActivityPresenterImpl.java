package com.money.peronainterviewproject_java_mvp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.money.peronainterviewproject_java_mvp.api.RetrofitManager;
import com.money.peronainterviewproject_java_mvp.json.WeatherObject;
import com.money.peronainterviewproject_java_mvp.json.WeatherTime;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenterImpl implements MainActivityPresenter{

    private final MainActivityVu mView;

    private final CompositeDisposable disposable;

    public MainActivityPresenterImpl(MainActivityVu mView) {
        this.mView = mView;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onActivityStart() {
        mView.showProgressBar(true);
        RetrofitManager.getRequestApi().getWeatherApi().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull WeatherObject weatherObject) {


                        if (weatherObject.getRecords() == null ||
                                weatherObject.getRecords().getLocationArrayList() == null ||
                                weatherObject.getRecords().getLocationArrayList().isEmpty() ||
                                weatherObject.getRecords().getLocationArrayList().get(0).getElementArrayList() == null ||
                                weatherObject.getRecords().getLocationArrayList().get(0).getElementArrayList().isEmpty() ||
                                weatherObject.getRecords().getLocationArrayList().get(0).getElementArrayList().get(0).getTimeArrayList() == null ||
                                weatherObject.getRecords().getLocationArrayList().get(0).getElementArrayList().get(0).getTimeArrayList().isEmpty()){

                            Log.i("Michael","??????????????????????????????");
                            mView.showToast(mView.getDataErrorString());
                            mView.showProgressBar(false);

                            return;
                        }
                        Log.i("Michael","??????????????????:"+weatherObject.getRecords().getDatasetDescription());

                        ArrayList<WeatherTime> timeArrayList = weatherObject.getRecords().getLocationArrayList().get(0).getElementArrayList().get(0).getTimeArrayList();

                        Log.i("Michael","TimeArray size : "+timeArrayList.size());

                        mView.showProgressBar(false);
                        mView.showWeatherInformation(timeArrayList);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("Michael","???????????????????????? : "+e);
                        mView.showToast(mView.getConnectTimeOut());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Michael","????????????????????????");
                    }
                });


    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }
}
