package com.money.peronainterviewproject_java_mvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.money.peronainterviewproject_java_mvp.json.WeatherTime;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainActivityVu{


    protected MainActivityPresenter presenter;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onActivityStart();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress_bar);
    }

    private void initPresenter() {
        presenter = new MainActivityPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public String getDataErrorString() {
        return getString(R.string.title_error_data_message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showWeatherInformation(ArrayList<WeatherTime> timeArrayList) {
        adapter = new MainAdapter();
        adapter.setTimeArrayList(timeArrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnWeatherTimeItemClickListener(new MainAdapter.OnWeatherTimeItemClickListener() {
            @Override
            public void onTimeClick(WeatherTime time) {
                Log.i("Michael","點擊:"+time.getStartTime());
                Intent it = new Intent(MainActivity.this,Page2Activity.class);
                it.putExtra("data",time);
                startActivity(it);
            }
        });
    }

    @Override
    public String getConnectTimeOut() {
        return getString(R.string.title_connect_time_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.setTimeArrayList(new ArrayList<>());
        adapter.notifyDataSetChanged();
    }
}