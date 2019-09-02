package com.fourtsoft.bvc.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(tableName = "setting_table")
public class Setting {

    public final static int ID = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ID})
    public @interface id {
    }

    public Setting(@id int id, boolean enableCBV, boolean isPermissionContact) {
        this.id = id;
        this.enableCBV = enableCBV;
        this.isPermissionContact = isPermissionContact;
    }

    public int getId() {
        return id;
    }

    public void setId(@id int id) {
        this.id = id;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "enableCBV")
    private boolean enableCBV;

    @ColumnInfo(name = "isPermissionContact")
    private boolean isPermissionContact;

    public boolean isEnableCBV() {
        return enableCBV;
    }

    public void setEnableCBV(boolean enableCBV) {
        this.enableCBV = enableCBV;
    }

    public boolean isPermissionContact() {
        return isPermissionContact;
    }

    public void setPermissionContact(boolean permissionContact) {
        isPermissionContact = permissionContact;
    }
}
