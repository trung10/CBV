package com.fourtsoft.bvc.homescreen.contactscreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.model.Contact;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ContactPresenter<V extends ContactContract.View> extends BasePresenter<V>
        implements ContactContract.Presenter<V> {

    private static final String TAG = ContactPresenter.class.getSimpleName();

    @Inject
    ContactPresenter(@NonNull Repository repository,
                     @NonNull CallApi callApi) {
        super(repository, callApi);
    }

    @Override
    public void loadContactList() {
        LogUtils.setLogSequenceMethod();
        getComposite().add(
                getPreference()
                        .getAllContact()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Contact>>() {
                            @Override
                            public void accept(List<Contact> contacts) throws Exception {
                                getView().showListContact(contacts);
                            }
                        }));
    }

    @Override
    public void detailContact(String id) {
        LogUtils.setLogSequenceMethod();
    }

    @Override
    public void requestPermissionContact() {
        getComposite().add(
                getRepository().getSetting().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Setting>() {
                    @Override
                    public void accept(Setting setting) {
                        if (setting.isPermissionContact()) {
                            getView().requestRequest();
                        }
                    }
                }));
    }
}
