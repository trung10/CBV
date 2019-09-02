package com.fourtsoft.bvc.homescreen.contactscreen;

import com.fourtsoft.bvc.base.BaseContract;
import com.fourtsoft.bvc.model.Contact;

import java.util.List;

public interface ContactContract {

    interface View extends BaseContract.View {

        void showListContact(List<Contact> contacts);

        void requestRequest();

    }

    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void loadContactList();

        void detailContact(String id);

        void requestPermissionContact();
    }
}
