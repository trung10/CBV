package com.fourtsoft.bvc.chargescreen;

import android.util.Log;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.model.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ChargeFragmentPresenter<V extends ChargeFragmentContact.View> extends BasePresenter<V> implements ChargeFragmentContact.Presenter<V> {

    @Inject
    ChargeFragmentPresenter(Repository repository,
                            CallApi callApi) {
        super(repository, callApi);
    }


    @Override
    public void getContact() {
        getComposite().add(getPreference()
                .getObserverPersonalContact("0909065386")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contactList) throws Exception {
                        if (contactList.size() == 0) {
                            Log.e("+++++++++", "deo co");
                        } else {
                            for (Contact contact : contactList) {
                                Log.e("+++++++++", "" + contact.getPhoto());
                            }
                        }

                    }
                }));
    }
}
