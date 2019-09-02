package com.fourtsoft.bvc.chargescreen;

import com.fourtsoft.bvc.base.BaseContract;

import javax.inject.Singleton;

public interface ChargeFragmentContact {

    interface View extends BaseContract.View {

    }

    @Singleton
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void getContact();
    }
}
