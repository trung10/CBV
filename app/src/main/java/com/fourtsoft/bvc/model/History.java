package com.fourtsoft.bvc.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class History {

    //================== enum ===========
    public final static int STATUS_MISS = 0;
    public final static int STATUS_REJECT = 1;
    public final static int STATUS_ACCEPT = 2;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_MISS, STATUS_REJECT, STATUS_ACCEPT})
    public @interface status {
    }

    public final static int TYPE_CALL_IN = 1;
    public final static int TYPE_CALL_OUT = 2;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_CALL_IN, TYPE_CALL_OUT})
    public @interface typeCall {
    }

    //============= field ===============
    private int id;

    private String nameContact;

    @status
    private int status;

    private String dateTimeContact;

    private String avatarContact;

    @typeCall
    private int typeCall;

    public History(@typeCall int typeCall, String nameContact, String dateTimeContact, String avatarContact, @status int status) {
        this.nameContact = nameContact;
        this.dateTimeContact = dateTimeContact;
        this.avatarContact = avatarContact;
        this.typeCall = typeCall;
        this.status = status;
    }

    public History(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeCall() {
        return typeCall;
    }

    public void setTypeCall(int typeCall) {
        this.typeCall = typeCall;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(@status int status) {
        this.status = status;
    }

    public String getAvatarContact() {
        return avatarContact;
    }

    public void setAvatarContact(String avatarContact) {
        this.avatarContact = avatarContact;
    }

}