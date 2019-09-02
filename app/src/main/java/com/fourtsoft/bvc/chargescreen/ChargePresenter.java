package com.fourtsoft.bvc.chargescreen;

import android.support.annotation.NonNull;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;

import javax.inject.Inject;

public class ChargePresenter<V extends ChargeContract.View> extends BasePresenter<V> implements ChargeContract.Presenter<V> {

    @Inject
    ChargePresenter(@NonNull Repository repository,
                    @NonNull CallApi callApi) {
        super(repository, callApi);
    }


}
