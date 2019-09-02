package com.fourtsoft.bvc.di.component;

import com.fourtsoft.bvc.chargescreen.ChargeActivity;
import com.fourtsoft.bvc.chargescreen.ChargeFragment;
import com.fourtsoft.bvc.di.AcScope;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.homescreen.HomeActivity;
import com.fourtsoft.bvc.homescreen.contactscreen.ContactFragment;
import com.fourtsoft.bvc.homescreen.dialscreen.DialNumberContract;
import com.fourtsoft.bvc.homescreen.dialscreen.DialNumberFragment;
import com.fourtsoft.bvc.homescreen.historyscreen.HistoryFragment;
import com.fourtsoft.bvc.homescreen.settingscreen.SettingFragment;
import com.fourtsoft.bvc.homescreen.themescreen.ThemeFragment;
import com.fourtsoft.bvc.incomming.InCommingActivity;
import com.fourtsoft.bvc.splash.SplashScreenActivity;

import dagger.Component;

@AcScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(HomeActivity homeActivity);

  void inject(ChargeActivity chargeActivity);

  void inject(SettingFragment settingFragment);

  void inject(ThemeFragment themeFragment);

  void inject(ContactFragment contactFragment);

  void inject(DialNumberFragment dialNumberFragment);

  void inject(HistoryFragment historyFragment);

  void inject(ChargeFragment chargeFragment);

  void inject(SplashScreenActivity screenActivity);
}
