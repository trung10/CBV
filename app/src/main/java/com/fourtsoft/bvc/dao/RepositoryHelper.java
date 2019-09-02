package com.fourtsoft.bvc.dao;

import android.content.Context;
import android.util.Log;

import com.fourtsoft.bvc.di.AppContext;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.model.ThemeSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RepositoryHelper implements Repository {

    private DaoApp mDaoApp;

    @Inject
    public RepositoryHelper(@AppContext Context context) {
        DatabaseRoom mDatabaseRoom = DatabaseRoom.getDatabase(context);
        mDaoApp = mDatabaseRoom.daoApp();
    }

    //======================= THEME TABLE =================================

    @Override
    public void setThemeSelected(ThemeSelected themeSelected) {
        ThemeSelect themeSelect = new ThemeSelect();
        themeSelect.setPath(themeSelected.getPath());
        mDaoApp.insertTheme(themeSelect);
    }

    @Override
    public ThemeSelected getThemeSelected() {
        List<ThemeSelect> themeSelect = mDaoApp.getThemes();
        if (themeSelect.size() == 0) {
            return null;
        }
        ThemeSelected themeSelected = new ThemeSelected();
        themeSelected.setPath(themeSelect.get(0).getPath());
        return themeSelected;
    }

    /*
     * DEFAULT THEME IS 0
     * */
    @Override
    public void updateThemeSelected(ThemeSelected themeSelected) {
        mDaoApp.updateThemeSelected(themeSelected.getPath(), 0);
    }

    //======================== HISTORY TABLE ==================================

    @Override
    public Flowable<List<History>> getAllHistory() {
        return Flowable.fromCallable(new Callable<List<History>>() {
            @Override
            public List<History> call() {
                List<com.fourtsoft.bvc.dao.History> histories = mDaoApp.getHistoris();
                List<History> list = new ArrayList<>();

                for (com.fourtsoft.bvc.dao.History history : histories) {
                    History mHistory = new History(history.getTypeCall(), history.getNameContact(),
                            history.getDateTimeContact(), history.getAvatarContact(), history.getStatus());
                    mHistory.setId(history.getId());
                    list.add(mHistory);
                }
                return list;
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<Boolean> insertHistory(final History history) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                com.fourtsoft.bvc.dao.History mHistory = new com.fourtsoft.bvc.dao.History(history.getNameContact(),
                        history.getDateTimeContact(), history.getAvatarContact(), history.getTypeCall(), history.getStatus());
                mDaoApp.insertHistory(mHistory);
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<Boolean> deleteHistory(final History history) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                com.fourtsoft.bvc.dao.History mHistory = new com.fourtsoft.bvc.dao.History(history.getNameContact(),
                        history.getDateTimeContact(),
                        history.getAvatarContact(),
                        history.getTypeCall(),
                        history.getStatus());
                mHistory.setId(history.getId());

                mDaoApp.deleteHistory(mHistory);
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<Boolean> deleteAll() {
        return Flowable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                mDaoApp.deleteAll();
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }

    //=========================== SETTING =================================

    @Override
    public void insertSetting(Setting setting) {
        mDaoApp.insertSetting(setting);
    }

    @Override
    public boolean isSettingExited() {

        List<Setting> settings = mDaoApp.getSetting();
        if (settings != null) {
            return settings.size() > 0 ? true : false;
        }
        return false;
    }

    @Override
    public Observable<Setting> getSetting() {

        return Observable.fromCallable(new Callable<Setting>() {
            @Override
            public Setting call() throws Exception {
                return mDaoApp.getSetting().get(0);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<Boolean> updateSetting(final Setting setting) {
        return Flowable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                boolean result = true;
                mDaoApp.updateSetting(setting.isEnableCBV(), setting.isPermissionContact());
                //get setting oke then return true else false
                return result;
            }
        }).subscribeOn(Schedulers.io());
    }
}