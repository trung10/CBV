package com.fourtsoft.bvc.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.homescreen.HomeActivity;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.utils.FileUtils;

import java.io.File;

import javax.inject.Inject;

public class SplashScreenActivity extends AppCompatActivity {

    private final int TIME_DELAY = 2000;

    @Inject
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        createFolderPrivate();
        delayToHomeScreen();
        insertSettingBeginer();
    }

    private void delayToHomeScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_DELAY);
    }

    private void createFolderPrivate() {
        FileUtils.createFolderIfNotExist(this);
        File file = new File(getCacheDir() + "/cbvVideo/", "default.mp4");
        if (!file.exists()) {
            FileUtils.copyAssets(this);
        }
    }

    private void insertSettingBeginer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (repository.isSettingExited() == false) {
                    repository.insertSetting(new Setting(Setting.ID, true, true));
                }
            }
        }).start();
    }
}