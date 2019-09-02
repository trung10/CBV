package com.fourtsoft.bvc.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fourtsoft.bvc.di.AppContext;
import com.fourtsoft.bvc.model.Setting;

@Database(entities = {ThemeSelect.class, History.class, Setting.class}, version = 1)
public abstract class DatabaseRoom extends RoomDatabase {

    public abstract DaoApp daoApp();

    private static DatabaseRoom INSTANCE;

    public static DatabaseRoom getDatabase(@AppContext Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            DatabaseRoom.class, "cbv_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}