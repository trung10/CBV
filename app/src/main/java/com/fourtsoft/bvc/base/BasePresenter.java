package com.fourtsoft.bvc.base;

import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Preference;
import com.fourtsoft.bvc.dao.Repository;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/*
 * This class is base all of Presenters.
 *
 * */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    private V v;
    private Repository mRepository;
    private CallApi mCallApi;
    private Preference mPreference;
    private CompositeDisposable mComposite;

    @Inject
    public BasePresenter(Repository repository,
                         CallApi callApi) {
        Objects.requireNonNull(repository, "repository");
        Objects.requireNonNull(callApi, "callApi");
        this.mRepository = repository;
        this.mCallApi = callApi;
    }

    /*
     * when app needed addition new class => simply add class to getListHistory method and create getter method for that variable
     * */
    @Inject
    public void init(Preference preference) {
        Objects.requireNonNull(preference, "repository");
        this.mPreference = preference;
        mComposite = new CompositeDisposable();
    }


    @Override
    public void onAttach(V v) {
        this.v = v;
    }

    @Override
    public void onDettach() {
        if (v != null) {
            v = null;
        }
        if (mComposite.isDisposed() == false) {
            mComposite.dispose();
        }
    }

    @Override
    public V getView() {
        return v;
    }

    @Override
    public boolean isViewAttach() {
        return v != null;
    }

    protected Repository getRepository() {
        return mRepository;
    }

    protected CallApi getCallApi() {
        return mCallApi;
    }

    protected Preference getPreference() {
        return mPreference;
    }

    protected CompositeDisposable getComposite() {
        return mComposite;
    }
}
