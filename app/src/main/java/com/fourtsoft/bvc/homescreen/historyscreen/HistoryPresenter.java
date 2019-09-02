package com.fourtsoft.bvc.homescreen.historyscreen;

import android.support.annotation.NonNull;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.dao.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class HistoryPresenter<V extends HistoryContract.View> extends BasePresenter<V>
        implements HistoryContract.Presenter<V> {

    private List<History> list;

    @Inject
    public HistoryPresenter(@NonNull Repository repository,
                            @NonNull CallApi callApi) {
        super(repository, callApi);
    }

    @Override
    public void deleteHistory(History history) {
        getComposite().add(getRepository()
                .deleteHistory(history)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void deleteAll() {
        getComposite().add(getRepository()
                .deleteAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }


    @Override
    public void getListHistory() {
        getComposite().add(getRepository()
                .getAllHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<History>>() {
                    @Override
                    public void accept(List<History> histories) {
                        getView().showListHistory(histories);
                    }
                })
        );
    }
}
