package com.fourtsoft.bvc.homescreen.settingscreen;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.ActivityComponent;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.model.Setting;
import com.fourtsoft.bvc.policyscreen.PolicyActivity;
import com.fourtsoft.bvc.termscreen.TermActivity;

import javax.inject.Inject;

/**
 * Create by pdtrung
 */

public class SettingFragment extends BaseFragment implements SettingFragmentContract.View, View.OnClickListener {

    private RelativeLayout rlTermOfUse;
    private RelativeLayout rlPolicy;
    private RelativeLayout rlRateApp;
    private RelativeLayout rlEnableCallReccord;
    private RelativeLayout rlEnablePermissionContact;

    private Switch swEnableCallRecord;
    private Switch swEnablePermissionContact;
    private Boolean isSettingScreenCreated = false;

    private View rootView;

    @Inject
    SettingPresenter<SettingFragmentContract.View> mPresenter;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();
        mActivityComponent.inject(this);
        rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(rootView);

        return rootView;
    }

    private void initView(View view) {
        rlTermOfUse = view.findViewById(R.id.rlTermOfUse);
        rlTermOfUse.setOnClickListener(this);

        rlPolicy = view.findViewById(R.id.rlPrivacyPolicy);
        rlPolicy.setOnClickListener(this);

        rlRateApp = view.findViewById(R.id.rlRateApp);
        rlRateApp.setOnClickListener(this);

        rlEnableCallReccord = view.findViewById(R.id.rlEnableCallReccord);
        rlEnableCallReccord.setOnClickListener(this);

        swEnableCallRecord = view.findViewById(R.id.swEnableCallRecord);
        swEnableCallRecord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeTrackColor(swEnableCallRecord, b);

                if (isSettingScreenCreated) {
                    mPresenter.updateSetting(new Setting(Setting.ID, b, swEnablePermissionContact.isChecked()));
                }
            }
        });

        rlEnablePermissionContact = view.findViewById(R.id.rlEnablePermissionContact);
        rlEnablePermissionContact.setOnClickListener(this);

        swEnablePermissionContact = view.findViewById(R.id.swEnablePermissionContact);
        swEnablePermissionContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeTrackColor(swEnablePermissionContact, b);

                if (isSettingScreenCreated) {
                    mPresenter.updateSetting(new Setting(Setting.ID, swEnableCallRecord.isChecked(), b));
                }
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);

        mPresenter.getSetting();
    }

    @Override
    public void showTermOfUse() {
        Intent intent = new Intent(getActivity(), TermActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void showPrivacypolicy() {
        Intent intent = new Intent(getActivity(), PolicyActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void rateApp() {
        //Dummy id = com.ketchapp.knifehit
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ketchapp.knifehit"));
        startActivity(intent);
    }

    @Override
    public void setDataSetting(Setting setting) {
//        if (setting )
        // todo set at view
        swEnableCallRecord.setChecked(setting.isEnableCBV());
        changeTrackColor(swEnableCallRecord, setting.isEnableCBV());

        swEnablePermissionContact.setChecked(setting.isPermissionContact());
        changeTrackColor(swEnablePermissionContact, setting.isPermissionContact());

        isSettingScreenCreated = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlPrivacyPolicy:
                showPrivacypolicy();
                break;

            case R.id.rlTermOfUse:
                showTermOfUse();
                break;

            case R.id.rlRateApp:
                rateApp();
                break;
            case R.id.rlEnableCallReccord:
                changeCheckedSwitch(swEnableCallRecord);
                break;

            case R.id.rlEnablePermissionContact:
                changeCheckedSwitch(swEnablePermissionContact);
                break;
        }
    }

    void changeCheckedSwitch(Switch aSwitch) {
        if (aSwitch.isChecked()) {
            aSwitch.setChecked(false);
        } else {
            aSwitch.setChecked(true);
        }
    }

    void changeTrackColor(Switch aSwitch, boolean b) {
        if (b) {
            aSwitch.getTrackDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.color_switch_check), PorterDuff.Mode.SRC_IN);
        } else {
            aSwitch.getTrackDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.color_switch_not_check), PorterDuff.Mode.SRC_IN);
        }
    }
}
