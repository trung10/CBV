package com.fourtsoft.bvc.chargescreen;

import com.fourtsoft.bvc.base.BaseContract;

public interface ChargeContract {

    interface View extends BaseContract.View {


    }

    interface Presenter<V extends View> extends BaseContract.Presenter<V> {


    }
}
