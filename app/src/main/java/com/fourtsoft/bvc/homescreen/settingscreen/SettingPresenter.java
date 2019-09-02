package com.fourtsoft.bvc.homescreen.settingscreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.model.Setting;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SettingPresenter<V extends SettingFragmentContract.View> extends BasePresenter<V>
        implements SettingFragmentContract.Presenter<V> {

    @Inject
    public SettingPresenter(@NonNull Repository repository,
                            @NonNull CallApi callApi) {
        super(repository, callApi);
    }

    @Override
    public void getSetting() {

        // TODO show loading
        getComposite().add(getRepository().getSetting()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Setting>() {
                    @Override
                    public void accept(Setting setting) {
                        // todo hide loading
                        getView().setDataSetting(setting);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        getView().showError();
                    }
                }));
    }

    @Override
    public void updateSetting(Setting setting) {

        getComposite().add(getRepository().updateSetting(setting)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            //todo update ui when setting
                        }
                    }
                }));
    }
}
