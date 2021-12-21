package com.money.peronainterviewproject_java_mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.money.peronainterviewproject_java_mvp.account.AccountManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccountManager.getInstance().setOnShowWelcomeInformation(new AccountManager.OnShowWelcomeInformation() {
            @Override
            public void onShowToast(String message) {
                Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        AccountManager.getInstance().countingEnterAppTimes();



    }
}
