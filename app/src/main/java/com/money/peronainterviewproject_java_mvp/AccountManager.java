package com.money.peronainterviewproject_java_mvp;

import com.money.peronainterviewproject_java_mvp.account.SharedPreferencesUtils;

public class AccountManager {

    private static AccountManager instance = null;

    private OnShowWelcomeInformation onShowWelcomeInformation;

    public void setOnShowWelcomeInformation(OnShowWelcomeInformation onShowWelcomeInformation){
        this.onShowWelcomeInformation = onShowWelcomeInformation;
    }

    public static AccountManager getInstance(){
        if (instance == null){
            instance = new AccountManager();
            return instance;
        }
        return instance;
    }

    private static final String COMEBACK_TIMES = "come_back_times";

    public void countingEnterAppTimes(){

        int count = SharedPreferencesUtils.getSharedPreferences(COMEBACK_TIMES).getInt(COMEBACK_TIMES,0);

        count ++;
        saveCount(count);
        if (count >= 2){
            onShowWelcomeInformation.onShowToast("歡迎回來.");
        }
    }

    private void saveCount(int count) {
        SharedPreferencesUtils.getSharedPreferences(COMEBACK_TIMES).edit().putInt(COMEBACK_TIMES,count).apply();
    }


    public interface OnShowWelcomeInformation{
        void onShowToast(String message);
    }


}
