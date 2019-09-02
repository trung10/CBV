package com.fourtsoft.bvc.dao;

import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.model.ThemeSelected;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface DatabaseApp {

    //=============================== THEME ===================================

    void setThemeSelected(ThemeSelected themeSelected);

    ThemeSelected getThemeSelected();

    void updateThemeSelected(ThemeSelected themeSelected);

    //================================ HISTORY ==================================

    Flowable<List<History>> getAllHistory();

    Flowable<Boolean> insertHistory(History history);

    Flowable<Boolean> deleteHistory(History history);

    Flowable<Boolean> deleteAll();

    //=============================== SETTING ====================================

    void insertSetting(Setting setting);

    boolean isSettingExited();

    Observable<Setting> getSetting();


    Flowable<Boolean> updateSetting(Setting setting);


}