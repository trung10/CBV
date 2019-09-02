package com.fourtsoft.bvc.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;

import com.fourtsoft.bvc.model.Setting;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(tableName = "history_table")
public class History {

    private final static int STATUS_MISS = 0;
    private final static int STATUS_REJECT = 1;
    private final static int STATUS_ACCEPT = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_MISS, STATUS_REJECT, STATUS_ACCEPT})
    public @interface status {
    }

    private final static int TYPE_CALL_IN = 1;
    private final static int TYPE_CALL_OUT = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_CALL_IN, TYPE_CALL_OUT})
    public @interface typeCall {
    }


    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nameContact")
    private String nameContact;

    @ColumnInfo(name = "dateTimeContact")
    private String dateTimeContact;

    @ColumnInfo(name = "avatarContact")
    private String avatarContact;

    @ColumnInfo(name = "typeCall")
    @typeCall
    private int typeCall;

    @ColumnInfo(name = "status")
    @status
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public History(String nameContact, String dateTimeContact, String avatarContact, int typeCall, @status int status) {
        this.nameContact = nameContact;
        this.dateTimeContact = dateTimeContact;
        this.avatarContact = avatarContact;
        this.typeCall = typeCall;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getDateTimeContact() {
        return dateTimeContact;
    }

    public void setDateTimeContact(String dateTimeContact) {
        this.dateTimeContact = dateTimeContact;
    }

    public String getAvatarContact() {
        return avatarContact;
    }

    public void setAvatarContact(String avatarContact) {
        this.avatarContact = avatarContact;
    }

    public int getTypeCall() {
        return typeCall;
    }

    public void setTypeCall(@typeCall int typeCall) {
        this.typeCall = typeCall;
    }
}