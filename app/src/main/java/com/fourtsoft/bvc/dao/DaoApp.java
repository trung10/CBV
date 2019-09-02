package com.fourtsoft.bvc.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.fourtsoft.bvc.model.Setting;

import java.util.List;

@Dao
public interface DaoApp {

    //======================== THEME ============================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTheme(ThemeSelect themeSelect);

    @Query("SELECT * FROM theme_table")
    List<ThemeSelect> getThemes();

    @Query("UPDATE theme_table set path =:pathTheme WHERE id =:id")
    void updateThemeSelected(String pathTheme, int id);


    //======================== HISTORY ==========================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistory(History history);

    @Delete
    void deleteHistory(History history);

    @Query("SELECT * FROM history_table")
    List<History> getHistoris();

    @Query("DELETE FROM history_table")
    void deleteAll();

    //======================== SETTING ==========================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSetting(Setting setting);

    @Query("SELECT * FROM setting_table")
    List<Setting> getSetting();

    @Query("UPDATE setting_table SET enableCBV =:enableCBV, isPermissionContact =:isPermissionContact WHERE id = 1")
    void updateSetting(Boolean enableCBV, Boolean isPermissionContact);
}

