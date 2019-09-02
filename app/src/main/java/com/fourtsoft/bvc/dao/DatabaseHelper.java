package com.fourtsoft.bvc.dao;

import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.model.ThemeSelected;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class DatabaseHelper implements DatabaseApp {

    //========================== THEME ==========================================

    @Override
    public void setThemeSelected(ThemeSelected themeSelected) {

    }

    @Override
    public ThemeSelected getThemeSelected() {
        return null;
    }

    @Override
    public void updateThemeSelected(ThemeSelected themeSelected) {
    }

    //========================== HISTORY =========================================

    @Override
    public Flowable<List<History>> getAllHistory() {
        return null;
    }

    @Override
    public Flowable<Boolean> insertHistory(History history) {
        return null;
    }

    @Override
    public Flowable<Boolean> deleteHistory(History history) {
        return null;
    }

    @Override
    public Flowable<Boolean> deleteAll() {
        return null;
    }

    //========================== SETTING =========================================

    @Override
    public void insertSetting(Setting setting) {

    }

    @Override
    public boolean isSettingExited() {
        return false;
    }

    @Override
    public Observable<Setting> getSetting() {
        return null;
    }

    @Override
    public Flowable<Boolean> updateSetting(Setting setting) {
        return null;
    }
}