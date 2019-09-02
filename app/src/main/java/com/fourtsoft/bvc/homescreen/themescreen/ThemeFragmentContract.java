package com.fourtsoft.bvc.homescreen.themescreen;

import android.support.annotation.NonNull;

import com.fourtsoft.bvc.base.BaseContract;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;

public interface ThemeFragmentContract {

    interface View extends BaseContract.View {

        void showListTheme(VideoList videoList);

        void showVideoItem(VideoItem videoItem);

    }

    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void detailVideoItem(@NonNull VideoItem videoItem);

    }

}
