package com.fourtsoft.bvc.homescreen.dialscreen;

import com.fourtsoft.bvc.base.BaseContract;

public interface DialNumberContract {
    interface View extends BaseContract.View{
        void deleteChar();
    }

    interface Presenter<V extends DialNumberContract.View> extends  BaseContract.Presenter<V> {

    }
}
