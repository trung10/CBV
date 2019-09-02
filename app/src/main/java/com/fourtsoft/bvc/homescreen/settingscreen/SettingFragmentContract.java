package com.fourtsoft.bvc.homescreen.settingscreen;

import com.fourtsoft.bvc.base.BaseContract;
import com.fourtsoft.bvc.model.Setting;

public interface SettingFragmentContract {

    interface View extends BaseContract.View{

        void showTermOfUse();

        void showPrivacypolicy();

        void rateApp();

        void setDataSetting(Setting setting);
    }

    interface Presenter<V extends View> extends  BaseContract.Presenter<V> {

        void getSetting();

        void updateSetting(Setting setting);
    }
}
