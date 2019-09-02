package com.fourtsoft.bvc.homescreen;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.fourtsoft.bvc.base.BasePresenter;
import com.fourtsoft.bvc.dao.CallApi;
import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.model.Setting;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class HomePresenter<V extends HomeContract.View> extends BasePresenter<V>
        implements HomeContract.Presenter<V> {
    private static final String TAG = HomePresenter.class.getSimpleName();

    @Inject
    HomePresenter(
            @NonNull Repository repository,
            @NonNull CallApi callApi) {
        super(repository, callApi);
    }

    @Override
    public void onAttach(V v) {
        super.onAttach(v);
    }

    @Override
    public void updatePermissionContacts(Setting setting) {
        getComposite().add(getRepository().updateSetting(setting)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void getSettingPermissionContacts() {
        getComposite().add(getRepository()
                .getSetting().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Setting>() {
                    @Override
                    public void accept(Setting setting) {
                        updatePermissionContacts(new Setting(setting.ID, setting.isEnableCBV(), false));
                    }
                }));
    }

    @Override
    public void getSettingPermissionCall(final boolean permission) {
        getComposite().add(getRepository()
                .getSetting().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Setting>() {
                    @Override
                    public void accept(Setting setting) {
                        updatePermissionContacts(new Setting(setting.ID, permission, setting.isPermissionContact()));
                    }
                }));
    }
}
