package com.fourtsoft.bvc.di.module;

import android.app.Activity;
import android.content.Context;

import com.fourtsoft.bvc.chargescreen.ChargeContract;
import com.fourtsoft.bvc.chargescreen.ChargeFragmentContact;
import com.fourtsoft.bvc.chargescreen.ChargeFragmentPresenter;
import com.fourtsoft.bvc.chargescreen.ChargePresenter;
import com.fourtsoft.bvc.di.AcScope;
import com.fourtsoft.bvc.di.ActivityContext;
import com.fourtsoft.bvc.homescreen.HomeContract;
import com.fourtsoft.bvc.homescreen.HomePresenter;
import com.fourtsoft.bvc.homescreen.contactscreen.ContactContract;
import com.fourtsoft.bvc.homescreen.contactscreen.ContactPresenter;
import com.fourtsoft.bvc.homescreen.dialscreen.DialNumberContract;
import com.fourtsoft.bvc.homescreen.dialscreen.DialNumberPresenter;
import com.fourtsoft.bvc.homescreen.historyscreen.HistoryContract;
import com.fourtsoft.bvc.homescreen.historyscreen.HistoryPresenter;
import com.fourtsoft.bvc.homescreen.settingscreen.SettingFragmentContract;
import com.fourtsoft.bvc.homescreen.settingscreen.SettingPresenter;

import com.fourtsoft.bvc.homescreen.themescreen.ThemeFragmentContract;
import com.fourtsoft.bvc.homescreen.themescreen.ThemePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @AcScope
    @ActivityContext
    Context providerContext() {
        return mActivity;
    }

    @Provides
    @AcScope
    public ChargeContract.Presenter<ChargeContract.View> provideChargePresenter(
            ChargePresenter<ChargeContract.View> chargePresenter) {
        return chargePresenter;
    }

    @Provides
    @AcScope
    public HomeContract.Presenter<HomeContract.View> provideHomePresenter(
            HomePresenter<HomeContract.View> homePresenter) {
        return homePresenter;
    }

    @Provides
    @AcScope
    public SettingFragmentContract.Presenter<SettingFragmentContract.View> provideSettingPresenter(
            SettingPresenter<SettingFragmentContract.View> settingPresenter) {
        return settingPresenter;
    }

    @Provides
    @AcScope
    public ThemeFragmentContract.Presenter<ThemeFragmentContract.View> provideThemePresenter(
            ThemePresenter<ThemeFragmentContract.View> themePresenter) {
        return themePresenter;
    }

    @Provides
    @AcScope
    public ContactContract.Presenter<ContactContract.View> provideContactPresenter(
            ContactPresenter<ContactContract.View> contactPresenter) {
        return contactPresenter;
    }

    @Provides
    @AcScope
    public DialNumberContract.Presenter<DialNumberContract.View> provideNumberPresenter(
            DialNumberPresenter<DialNumberContract.View> dialNumberPresenter) {
        return dialNumberPresenter;
    }

    @Provides
    @AcScope
    public HistoryContract.Presenter<HistoryContract.View> provideHistoryPresenter(
            HistoryPresenter<HistoryContract.View> historyPresenter) {
        return historyPresenter;
    }

    @Provides
    @AcScope
    public ChargeFragmentContact.Presenter<ChargeFragmentContact.View> provideChargeFrPresenter(
            ChargeFragmentPresenter<ChargeFragmentContact.View> presenter) {
        return presenter;
    }

}
