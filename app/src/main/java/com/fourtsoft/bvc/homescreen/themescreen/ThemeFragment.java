package com.fourtsoft.bvc.homescreen.themescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.dialogs.ThemeSelectionDialog;
import com.fourtsoft.bvc.model.DownloadVideo;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;
import com.fourtsoft.bvc.utils.LogUtils;
import com.fourtsoft.bvc.utils.ViewUtils;

import javax.inject.Inject;

public class ThemeFragment extends BaseFragment implements ThemeFragmentContract.View {

    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = ThemeFragment.class.getSimpleName();

    @Inject
    ThemeFragmentContract.Presenter<ThemeFragmentContract.View> mPresenter;
    private RecyclerView mListThemeView;

    public static ThemeFragment newInstance() {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.setLogSequenceMethod();
        DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build().inject(this);
        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        LogUtils.setLogSequenceMethod();
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
        mListThemeView = view.findViewById(R.id.list_theme);
        registerReceiver();
    }

    @Override
    public void showListTheme(VideoList videoList) {
        LogUtils.setLogSequenceMethod();
        if (mListThemeView == null) {
            return;
        }
        mListThemeView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL,
                false));

        mListThemeView.setAdapter(ListThemeAdapter.getInstance(videoList.getVideoItem(),
                new ThemeFragmentListener() {
                    @Override
                    public void clickThemeLoad(VideoItem videoItem) {
                        mPresenter.detailVideoItem(videoItem);

                        //todo download
                        ViewUtils.showDialogThemeSlection((AppCompatActivity) getActivity(), videoItem, new ThemeSelectionDialog.ThemeSelectListener() {
                            @Override
                            public void onClose() {

                            }

                            @Override
                            public void onSelectTheme(VideoItem videoItem) {
                                //todo change theme for call incoming
                            }
                        });
                    }
                }));
    }

    @Override
    public void showVideoItem(VideoItem videoItem) {

    }

    private void registerReceiver() {
        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {
                DownloadVideo download = intent.getParcelableExtra("download");
                Log.e(TAG, "progress = > " + download.getProgress());
            }
        }
    };
}