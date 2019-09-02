package com.fourtsoft.bvc.di.component;

import android.content.Context;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Preference;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.di.AppContext;
import com.fourtsoft.bvc.di.module.ApplicationModule;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @AppContext
    Context getContext();

    Repository getRepository();

    CallApi getCallApi();

    Preference getPreference();
}
