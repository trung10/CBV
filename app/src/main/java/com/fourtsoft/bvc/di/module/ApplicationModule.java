package com.fourtsoft.bvc.di.module;

import android.app.Application;
import android.content.Context;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.CallApiHelper;
import com.fourtsoft.bvc.dao.Preference;
import com.fourtsoft.bvc.dao.PreferenceHelper;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.dao.RepositoryHelper;
import com.fourtsoft.bvc.di.AppContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {


  private Application mApplication;


  public ApplicationModule(Application application) {
    this.mApplication = application;

  }

  @Provides
  @AppContext Context providesAppContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  Repository providesRepositoryHelper(RepositoryHelper repositoryHelper){
    return repositoryHelper;
  }

  @Provides
  @Singleton
  CallApi providesCallApiHelper(CallApiHelper callApiHelper){
    return callApiHelper;
  }

  @Provides
  @Singleton
  Preference providesPreferenceHelper(PreferenceHelper preferenceHelper){
    return preferenceHelper;
  }

}
