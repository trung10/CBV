package com.fourtsoft.bvc.homescreen.themescreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;
import com.fourtsoft.bvc.utils.LogUtils;
import com.fourtsoft.bvc.utils.ViewUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class ThemePresenter<V extends ThemeFragmentContract.View> extends BasePresenter<V>
        implements ThemeFragmentContract.Presenter<V> {

    private static final String TAG = ThemePresenter.class.getSimpleName();

    @Inject
    ThemePresenter(
            @NonNull Repository repository,
            @NonNull CallApi callApi) {
        super(repository, callApi);
    }

    @Override
    public void onAttach(V v) {
        super.onAttach(v);
        LogUtils.setLogSequenceMethod();
        if (getView().isHaveInternet()) {
            getComposite()
                    .add(getCallApi()
                            .getVideoList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext(new Consumer<VideoList>() {
                                @Override
                                public void accept(VideoList videoList) {
                                    getView().showListTheme(videoList);
                                }
                            })
                            .doOnError(new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) {
                                    getView().showError();
                                }
                            }).subscribe());

        } else {
            /*
             * not have internet
             * */
        }

    }

    @Override
    public void detailVideoItem(@NonNull VideoItem videoItem) {
        LogUtils.setLogSequenceMethod();
        if (getView().isHaveInternet()) {
            getCallApi().downloadVideoItem(videoItem);
        }

    }
}
