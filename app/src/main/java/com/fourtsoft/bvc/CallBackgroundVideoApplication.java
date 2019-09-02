package com.fourtsoft.bvc;

import android.app.Application;
import android.content.res.Configuration;

import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.dao.RepositoryHelper;
import com.fourtsoft.bvc.di.component.ApplicationComponent;
import com.fourtsoft.bvc.di.component.DaggerApplicationComponent;
import com.fourtsoft.bvc.di.module.ApplicationModule;
import com.fourtsoft.bvc.utils.LogUtils;

import javax.inject.Inject;

public class CallBackgroundVideoApplication extends Application {

    public static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.setLogSequenceMethod();
    }
}