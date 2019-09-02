package com.fourtsoft.bvc.homescreen;

import com.fourtsoft.bvc.base.BaseContract;
import com.fourtsoft.bvc.model.Setting;

public interface HomeContract {

    interface View extends BaseContract.View {

    }

    interface Presenter<V extends View> extends BaseContract.Presenter<V> {
        void updatePermissionContacts(Setting setting);

        void getSettingPermissionContacts();

        void getSettingPermissionCall(boolean permission);
    }

}
