package com.money.peronainterviewproject_java_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.money.peronainterviewproject_java_mvp.json.WeatherTime;

public class Page2Activity extends AppCompatActivity {

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        tvInfo = findViewById(R.id.tv_information);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null){
            return;
        }

        WeatherTime data = (WeatherTime) bundle.getSerializable("data");


        if (data == null || data.getParameter() == null){
            return;
        }

        tvInfo.setText(String.format("%s\n%s\n%S",data.getStartTime(),data.getEndTime(),data.getParameter().getParameterName()+data.getParameter().getParameterUnit()));

    }
}