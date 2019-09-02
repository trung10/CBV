package com.fourtsoft.bvc.dao;

import com.fourtsoft.bvc.model.Contact;

import java.util.List;

import io.reactivex.Flowable;

/*
 * this interface is helper for get Data from ContentProvider , File , ...
 * */
public interface Preference {

    Flowable<List<Contact>> getAllContact();

    Flowable<List<Contact>> getObserverPersonalContact(String mobileNumber);

    List<Contact> getPersonalContact(String mobileNumber);

}
