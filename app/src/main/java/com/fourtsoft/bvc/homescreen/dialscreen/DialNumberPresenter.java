package com.fourtsoft.bvc.homescreen.dialscreen;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;

import javax.inject.Inject;

public class DialNumberPresenter<V extends DialNumberContract.View> extends BasePresenter<V>
        implements DialNumberContract.Presenter<V> {

    @Inject
    public DialNumberPresenter(Repository repository, CallApi callApi) {
        super(repository, callApi);
    }
}
